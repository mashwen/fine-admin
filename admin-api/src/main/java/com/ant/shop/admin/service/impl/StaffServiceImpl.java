package com.ant.shop.admin.service.impl;

import com.ant.shop.admin.service.FineAdminLogService;
import com.ant.shop.admin.service.StaffService;
import com.ant.shop.admin.utils.PageInfo;
import com.ant.shop.asorm.entity.FineAdminLog;
import com.ant.shop.asorm.entity.FineStaff;
import com.ant.shop.asorm.entity.FineStaffOrgRoleKey;
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
    public ResultModel addStaff(StaffModel staffModel) {
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

//        FineAdminLog fineAdminLog = new FineAdminLog();
//        fineAdminLog.setRefTable("fine_staff");
//        fineAdminLog.setRefId("1,2,3");
//        fineAdminLog.setContent(JsonUtil.toJson(staff));
//        fineAdminLog.setOperation(LogModelEnum.LogOperationNameEnum.CREATE_STAFF.getValue());
//        fineAdminLog.setCreated(new Date());
//        fineAdminLog.setCreatedBy(1);
//        fineAdminLogService.insertLog(fineAdminLog);

        Map<Object, List> orgRole = staffModel.getOrgRole();
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
        return ResultModel.ok();
    }

    @Override
    public ResultModel staffList(String fullname, String mobile, String email, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<FineStaff> staffList = fineStaffMapper.selectStaffList(fullname, mobile, email);
        if (staffList == null || staffList.size() == 0){
            ResultModel.error("该组织没有员工");
        }
        int count = staffList.size();
        int totalPage = 0;
        if (count % pageSize == 0){
            totalPage = count / pageSize;
        }else {
            totalPage = count / pageSize + 1;
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
        PageInfo pageInfo = new PageInfo(page, page - 1, nextPage, count, pageSize, totalPage);
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
    public ResultModel staffStatus(FineStaff fineStaff) {
        int i = fineStaffMapper.updateByPrimaryKeySelective(fineStaff);
        if (i > 0){
            return ResultModel.ok();
        }
        return ResultModel.error("修改失败");
    }
}
