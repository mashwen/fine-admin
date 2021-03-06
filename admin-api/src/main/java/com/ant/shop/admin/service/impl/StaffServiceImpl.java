package com.ant.shop.admin.service.impl;

import com.ant.shop.admin.service.FineAdminLogService;
import com.ant.shop.admin.service.StaffService;
import com.ant.shop.admin.utils.PageInfo;
import com.ant.shop.asorm.entity.FineAdminLog;
import com.ant.shop.asorm.entity.FineStaff;
import com.ant.shop.asorm.mapper.FineOrgMapper;
import com.ant.shop.asorm.mapper.FineRoleMapper;
import com.ant.shop.asorm.mapper.FineStaffMapper;
import com.ant.shop.asorm.mapper.FineStaffOrgRoleMapper;
import com.ant.shop.asorm.model.StaffModel;
import com.ant.shop.asorm.model.StaffOrgRoleModel;
import com.github.pagehelper.PageHelper;
import enums.LogModelEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import response.ResultModel;
import utils.BryptUtils;
import utils.JsonUtil;

import java.util.*;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private FineStaffMapper fineStaffMapper;
    @Autowired
    private FineStaffOrgRoleMapper fineStaffOrgRoleMapper;
    @Autowired
    private FineOrgMapper fineOrgMapper;
    @Autowired
    private FineRoleMapper fineRoleMapper;
    @Autowired
    private FineAdminLogService fineAdminLogService;

    @Override
    @Transactional
    public ResultModel addStaff(StaffModel staffModel, Integer userId) {
        if (staffModel.getEmail() == null && staffModel.getMobile() == null){
            return ResultModel.error("联系方式和邮箱至少填写填写一个");
        }
        FineStaff s = new FineStaff();
        FineAdminLog fineAdminLog = new FineAdminLog();
        if (staffModel.getFullname() != null){
            s = fineStaffMapper.selectStaffByMobile(staffModel.getMobile());
        }
        if (staffModel.getEmail() != null){
            s = fineStaffMapper.selectStaffByEmail(staffModel.getEmail());
        }
        if (s != null){
            return ResultModel.error("联系方式或邮箱已存在");
        }
        FineStaff staff = new FineStaff();
        staff.setFullname(staffModel.getFullname());
        staff.setEmail(staffModel.getEmail());
        staff.setPassword(BryptUtils.brypt(staffModel.getPassword()));
        Byte gender = staffModel.getGender();
        boolean staffGender = true;
        if (gender == 2){
            staffGender = false;
        }
        staff.setGender(staffGender);
        staff.setJobTitle(staffModel.getJobTitle());
        staff.setMobile(staffModel.getMobile());
        staff.setAvatar(staffModel.getAvatar());
        staff.setCreated(new Date());
        staff.setStatus((byte) 1);
        int i = fineStaffMapper.insertSelective(staff);
        if (i == 0){
            ResultModel.error("添加失败");
        }
        Map<Object, List> orgRole = staffModel.getOrgRole();
        if(orgRole != null){
            for (Map.Entry<Object, List> orgRoleId : orgRole.entrySet()){
                int orgId = Integer.parseInt(orgRoleId.getKey().toString());
                List roleIdList = orgRoleId.getValue();
                for (Object roleId : roleIdList) {
                    StaffOrgRoleModel fineStaffOrgRoleKey = new StaffOrgRoleModel();
                    fineStaffOrgRoleKey.setOrgId(orgId);
                    int role = Integer.parseInt(roleId.toString());
                    fineStaffOrgRoleKey.setRoleId(role);
                    fineStaffOrgRoleKey.setStaffId(staff.getId());
                    fineStaffOrgRoleKey.setIncludeAll(true);
                    fineStaffOrgRoleMapper.insertStaffRoleOrg(fineStaffOrgRoleKey);
                }
            }
        }
        fineAdminLog.setRefTable("fine_staff");
        fineAdminLog.setRefId(staff.getId()+"");
        fineAdminLog.setContent(JsonUtil.toJson(staff));
        fineAdminLog.setOperation(LogModelEnum.LogOperationNameEnum.CREATE_STAFF.getValue());
        fineAdminLog.setCreated(new Date());
        fineAdminLog.setCreatedBy(userId);
        fineAdminLogService.insertLog(fineAdminLog);
        return ResultModel.ok();
    }

    @Override
    public ResultModel staffList(String fullname, String mobile, String email, int page, int pageSize) {
        if (fullname == ""){
            fullname = null;
        }
        if (mobile== ""){
            mobile = null;
        }
        if (email == ""){
            email = null;
        }
        Integer staffCount = fineStaffMapper.selectStaffCount(fullname, mobile, email);
        if (staffCount == null){
            return ResultModel.error("该组织没有员工");
        }
        PageHelper.startPage(page, pageSize);
        List<FineStaff> staffList = fineStaffMapper.selectStaffList(fullname, mobile, email);
        if (staffList == null || staffList.size() == 0){
            ResultModel.error("该组织没有员工");
        }
        int totalPage = 0;
        if (staffCount % pageSize == 0){
            totalPage = staffCount / pageSize;
        }else {
            totalPage = staffCount / pageSize + 1;
        }
        int nextPage = page + 1;
        if (page == totalPage){
            nextPage = 0;
        }
        List<StaffModel> staffModelsList = new ArrayList<>();
        for (FineStaff staff : staffList) {
            StaffModel staffModel = new StaffModel();
            BeanUtils.copyProperties(staff, staffModel);
            if (staff.getGender()){
                staffModel.setGender((byte)1);
            }else {
                staffModel.setGender((byte)2);
            }
            staffModelsList.add(staffModel);
        }
        PageInfo pageInfo = new PageInfo(page, page - 1, nextPage, staffCount, pageSize, totalPage);
        Map<String, Object> map = new HashMap<>();
        map.put("staffList", staffModelsList);
        map.put("pageInfo", pageInfo);
        return ResultModel.ok(map);
    }

    @Override
    public ResultModel staffDetails(int id) {
        FineStaff staff = fineStaffMapper.selectByPrimaryKey(id);
        if (staff == null){
            return ResultModel.error("该员工不存在");
        }
        staff.setPassword(null);
        StaffModel staffModel = new StaffModel();
        BeanUtils.copyProperties(staff, staffModel);
        List org = fineStaffOrgRoleMapper.selectOrg(id);
        Map<Object, List> orgRole = new HashMap<>();
        for (Object orgId : org) {
            String orgName = fineOrgMapper.selectNameById((int) orgId);
            List<List> roleIdList = fineStaffOrgRoleMapper.selectRole(id, (int) orgId);
            List<String> roleNameList = new ArrayList<>();
            for (Object roleId : roleIdList) {
                String roleName = fineRoleMapper.selectNameById((int) roleId);
                roleNameList.add(roleName);
            }
           orgRole.put(orgName, roleNameList);
        }
        staffModel.setOrgRole(orgRole);
        Map<String, Object> map = new HashMap<>();
        if (staff.getGender()){
            staffModel.setGender((byte) 1);
        }else {
            staffModel.setGender((byte) 2);
        }
        map.put("staff", staffModel);
        return ResultModel.ok(map);
    }

    @Override
    @Transactional
    public ResultModel staffStatus(FineStaff fineStaff, Integer userId) {
        int i = fineStaffMapper.updateByPrimaryKeySelective(fineStaff);
        if (i > 0){
            FineAdminLog fineAdminLog = new FineAdminLog();
            fineAdminLog.setRefTable("fine_staff");
            fineAdminLog.setRefId(fineStaff.getId() + "");
            fineAdminLog.setContent(JsonUtil.toJson(fineStaff));
            fineAdminLog.setOperation(LogModelEnum.LogOperationNameEnum.UPDATE_ROLE.getValue());
            fineAdminLog.setCreated(new Date());
            fineAdminLog.setCreatedBy(userId);
            fineAdminLogService.insertLog(fineAdminLog);
            return ResultModel.ok();
        }
        return ResultModel.error("修改失败");
    }

    @Override
    public ResultModel staffPwd(FineStaff fineStaff, Integer userId) {
        if (fineStaff.getId() == null || fineStaff.getId() == 0){
            return ResultModel.error("员工id不能为空");
        }
        if (fineStaff.getPassword() == null || fineStaff.getPassword() == ""){
            return ResultModel.error("密码不能为空");
        }
        FineStaff staff = fineStaffMapper.selectByPrimaryKey(fineStaff.getId());
        if (staff == null){
            return ResultModel.error("该用户不存在");
        }
        staff.setPassword(BryptUtils.brypt(fineStaff.getPassword()));
        int i = fineStaffMapper.updateByPrimaryKey(staff);
        if (i > 0){
            FineAdminLog fineAdminLog = new FineAdminLog();
            fineAdminLog.setRefTable("fine_staff");
            fineAdminLog.setRefId(staff.getId() + "");
            fineAdminLog.setContent(JsonUtil.toJson(staff));
            fineAdminLog.setOperation(LogModelEnum.LogOperationNameEnum.UPDATE_ROLE.getValue());
            fineAdminLog.setCreated(new Date());
            fineAdminLog.setCreatedBy(userId);
            fineAdminLogService.insertLog(fineAdminLog);
            return ResultModel.ok();
        }
        return ResultModel.error("修改失败!");
    }
}
