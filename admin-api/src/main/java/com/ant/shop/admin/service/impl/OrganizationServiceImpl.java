package com.ant.shop.admin.service.impl;

import com.ant.shop.admin.service.OrganizationService;
import com.ant.shop.asorm.entity.*;
import com.ant.shop.asorm.mapper.*;
import com.ant.shop.asorm.model.AddOrganizationDTO;
import com.ant.shop.asorm.model.OrganizationDTO;
import com.ant.shop.asorm.model.PageDTO;
import com.ant.shop.asorm.model.PageListResp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import response.ResultModel;
import utils.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author liuzongqiang
 * @Date 2019/6/22 0022 19:41
 * @Version 1.0
 **/
@Service
@Slf4j
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private FineOrgMapper fineOrgMapper;
    @Autowired
    private FineOrgDistrictMapper fineOrgDistrictMapper;
    @Autowired
    private FineAdminFieldDataMapper fineAdminFieldDataMapper;
    @Autowired
    private FineAdminFieldMapper fineAdminFieldMapper;
    @Autowired
    private FineStoreMapper fineStoreMapper;
    @Autowired
    private FineStaffOrgRoleMapper fineStaffOrgRoleMapper;



    /**
     * 启用/禁用 组织
     * @param id
     * @param enabled
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultModel setOrganizationEnabled(Integer id, Boolean enabled) {

        FineOrg isOrg = fineOrgMapper.selectByPrimaryKey(id);
        if(isOrg==null){
            return ResultModel.error("没有该组织！");
        }
        //修改的参数
        FineOrg fineOrg = new FineOrg();
        fineOrg.setIsEnabled(enabled);
        //条件
        FineOrgExample fineOrgExample=new FineOrgExample();
        fineOrgExample.createCriteria().andIdEqualTo(id);
        try {
            //执行sql
            int i = fineOrgMapper.updateByExampleSelective(fineOrg, fineOrgExample);
            if(i<=0){
                return ResultModel.error("启用/禁用失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultModel.error("启用/禁用失败！");
        }
        return ResultModel.ok();
    }

    /**
     * 删除组织
     * @param id
     * @return
     */
    @Override
    @Transactional
    public ResultModel deleteOrganizationById(Integer id) {
        try {
            //删除前先判断该组织是否启用状态，如果不是启用状态，则可以删除
            FineOrg fineOrg = fineOrgMapper.selectByPrimaryKey(id);
            if(fineOrg==null){
                return ResultModel.error("没有该组织！");
            }else if(fineOrg.getIsEnabled()){
                return ResultModel.error("启用状态下的组织不可以被删除!");
            }
            //1、先删除组织
            fineOrgMapper.deleteByPrimaryKey(id);

            //2、删除组织行政区域
            FineOrgDistrictExample fineOrgDistrictExample=new FineOrgDistrictExample();
            fineOrgDistrictExample.createCriteria().andOrgIdEqualTo(id);
            fineOrgDistrictMapper.deleteByExample(fineOrgDistrictExample);
            //3、删除自定义信息
            List<AddOrganizationDTO.FineAdminFields> fineAdminFields = fineAdminFieldDataMapper.selectByRefId(id);
            FineAdminFieldDataExample fineAdminFieldDataExample;
            for (AddOrganizationDTO.FineAdminFields fineAdminField : fineAdminFields) {
                fineAdminFieldDataExample =new FineAdminFieldDataExample();
                fineAdminFieldDataExample.createCriteria().andFieldIdEqualTo(fineAdminField.getFieldId())
                        .andRefIdEqualTo(fineAdminField.getRefId());
                fineAdminFieldDataMapper.deleteByExample(fineAdminFieldDataExample);
            }
            //4、删除门店
            FineStoreExample fineStoreExample=new FineStoreExample();
            fineStoreExample.createCriteria().andOrgIdEqualTo(id);
            fineStoreMapper.deleteByExample(fineStoreExample);

            //5、删除员工组织角色关系
            FineStaffOrgRoleExample fineStaffOrgRoleExample=new FineStaffOrgRoleExample();
            fineStaffOrgRoleExample.createCriteria().andOrgIdEqualTo(id);
            fineStaffOrgRoleMapper.deleteByExample(fineStaffOrgRoleExample);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultModel.error("删除失败！");
        }

        return ResultModel.ok();

    }

    /**
     * 筛选组织
     *
     * @param type
     * @param enabled
     * @param keyword
     * @return
     */
    @Override
    public ResultModel getOrganizationByKeyword(Byte type, Boolean enabled, String keyword,Integer pageNum, Integer pageSize) {
        Map<String,Object> data=new HashMap<>(16);

        if(pageNum<0 || pageSize<0){
            return ResultModel.error("0","页码与页大小不能为负数！");
        }

        OrganizationDTO organizationDTO=new OrganizationDTO();
        organizationDTO.setIsEnabled(enabled);
        organizationDTO.setType(type);

        if(keyword!=null && !keyword.equals("")){
            if(StringUtils.isInteger(keyword)){
                organizationDTO.setCode(keyword);
            }else{
                organizationDTO.setName(keyword);
            }
        }
        //获取组织筛选后的数据列表
        List<OrganizationDTO> list = fineOrgMapper.selectByKeyword(organizationDTO);
        //设置分页数据
        PageHelper.startPage(pageNum,pageSize);
        //获取分页列表
        List<OrganizationDTO> fineOrgList = fineOrgMapper.selectByKeyword(organizationDTO);
        PageInfo<FineOrg> pageInfo = new PageInfo(fineOrgList);
        PageDTO pageDTO = new PageDTO();

        pageDTO.setCountPerPage(pageInfo.getPageSize());
        pageDTO.setNextPage(pageInfo.getNextPage());
        pageDTO.setPage(pageInfo.getPageNum());
        pageDTO.setPrevPage(pageInfo.getPrePage());
        pageDTO.setTotalCount(list.size());
        pageDTO.setTotalPage(pageInfo.getPages());

        PageListResp pageList = new PageListResp();
        pageList.setList(fineOrgList);
        pageList.setPagination(pageDTO);


        data.put("orgList",pageList);
        return ResultModel.ok(data);

    }

    /**
     * 组织列表
     * @param parentId
     * @return
     */
    @Override
    public List<OrganizationDTO> organizationList(Integer parentId) {
        //获取parentId为0的列表
        List<OrganizationDTO> orgList = fineOrgMapper.selectByParentId(parentId);
        if(orgList.size()==0){
            return orgList;
        }
        for (OrganizationDTO org : orgList) {
            List<OrganizationDTO> list = organizationList(org.getId());
            org.setChildList(list);
        }
        return orgList;
    }


    /**
     * 新增组织
     *
     * @param addOrganizationDTO
     */
    @Override
    @Transactional
    public ResultModel setOrganization(AddOrganizationDTO addOrganizationDTO) {
        try {
            //1、添加组织基本信息
            FineOrg fineOrg=new FineOrg();
            BeanUtils.copyProperties(addOrganizationDTO,fineOrg);
            fineOrg.setCreated(new Date());
            fineOrgMapper.insert(fineOrg);
            //2、添加组织行政区域
            FineOrgDistrictKey fineOrgDistrict=new FineOrgDistrictKey();
            log.info(fineOrg.getId()+"--"+addOrganizationDTO.getDistrictId());
            fineOrgDistrict.setOrgId(fineOrg.getId());
            fineOrgDistrict.setDistrictId(addOrganizationDTO.getDistrictId());
            fineOrgDistrictMapper.insert(fineOrgDistrict);
            //3、添加更多组织信息
            List<AddOrganizationDTO.FineAdminFields> fineAdminFieldDataList = addOrganizationDTO.getFineAdminFieldDataList();

            for (AddOrganizationDTO.FineAdminFields fineAdminFields : fineAdminFieldDataList) {
                log.info(fineAdminFields.getValue()+"--"+fineAdminFields.getFieldId());
                FineAdminFieldData fineAdminFieldData=new FineAdminFieldData();
                BeanUtils.copyProperties(fineAdminFields,fineAdminFieldData);
                fineAdminFieldData.setRefId(fineOrg.getId());
                fineAdminFieldData.setCreated(new Date());
                fineAdminFieldDataMapper.insert(fineAdminFieldData);
            }
            //4、如果是门店的话需要增加门店信息
            FineStore fineStore = addOrganizationDTO.getFineStore();
            if(fineStore!=null){
                fineStore.setOrgId(fineOrg.getId());
                fineStore.setCreated(new Date());
                fineStoreMapper.insert(fineStore);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultModel.error("新增失败！");
        }
        return ResultModel.ok();
    }

    /**
     * 组织详情
     * @param id
     * @return
     */
    @Override
    public ResultModel getOrganizationById(Integer id) {
        AddOrganizationDTO addOrganizationDTO=new AddOrganizationDTO();

        //组织基本信息
        FineOrg fineOrg = fineOrgMapper.selectByPrimaryKey(id);
        if(fineOrg==null){
            return ResultModel.error("没有该组织！");
        }
        BeanUtils.copyProperties(fineOrg,addOrganizationDTO);
        //组织行政关系
        FineOrgDistrictExample fineOrgDistrictExample=new FineOrgDistrictExample();
        fineOrgDistrictExample.createCriteria().andOrgIdEqualTo(fineOrg.getId());
        List<FineOrgDistrictKey> fineOrgDistrictKeys = fineOrgDistrictMapper.selectByExample(fineOrgDistrictExample);
        //更多信息
        List<AddOrganizationDTO.FineAdminFields> fineAdminFields = fineAdminFieldDataMapper.selectByRefId(id);


        //店铺信息
        FineStoreExample fineStoreExample=new FineStoreExample();
        fineStoreExample.createCriteria().andOrgIdEqualTo(id);
        List<FineStore> fineStores = fineStoreMapper.selectByExample(fineStoreExample);

        //计算员工人数
        if(fineStores!=null && fineStores.size()!=0){
            Integer staffCount = fineStaffOrgRoleMapper.countByOrgId(id);
            addOrganizationDTO.setStaffCount(staffCount);
        }
        addOrganizationDTO.setFineStore(fineStores.size()==0?null:fineStores.get(0));
        addOrganizationDTO.setFineAdminFieldDataList(fineAdminFields);

        Map<String,Object> data=new HashMap<>(16);
        data.put("organization",addOrganizationDTO);
        return ResultModel.ok(data);
    }

    /**
     * 编辑组织
     *
     * @param addOrganizationDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultModel updateOrganization(AddOrganizationDTO addOrganizationDTO) {

        try {
            //1、修改组织基本信息
            FineOrg fineOrg=new FineOrg();
            BeanUtils.copyProperties(addOrganizationDTO,fineOrg);
            FineOrgExample fineOrgExample=new FineOrgExample();
            fineOrgExample.createCriteria().andIdEqualTo(fineOrg.getId());
            fineOrgMapper.updateByExampleSelective(fineOrg,fineOrgExample);

            //2、修改组织行政区域信息
            FineOrgDistrictKey orgDistrict = new FineOrgDistrictKey();
            log.info(addOrganizationDTO.getDistrictId().toString());
            orgDistrict.setDistrictId(addOrganizationDTO.getDistrictId());
            orgDistrict.setOrgId(addOrganizationDTO.getId());
            FineOrgDistrictExample fineOrgDistrictExample=new FineOrgDistrictExample();
            fineOrgDistrictExample.createCriteria().andOrgIdEqualTo(addOrganizationDTO.getId());
            fineOrgDistrictMapper.updateByExampleSelective(orgDistrict,fineOrgDistrictExample);

            //3、修改更多组织信息
            List<AddOrganizationDTO.FineAdminFields> fineAdminFieldDataList = addOrganizationDTO.getFineAdminFieldDataList();
            FineAdminFieldDataExample fineAdminFieldDataExample=new FineAdminFieldDataExample();
            for (AddOrganizationDTO.FineAdminFields fineAdminFields : fineAdminFieldDataList) {
                FineAdminFieldData fineAdminFieldData=new FineAdminFieldData();
                BeanUtils.copyProperties(fineAdminFields,fineAdminFieldData);
                fineAdminFieldDataMapper.updateByPrimaryKeySelective(fineAdminFieldData);
            }

            //4、如果是门店的话需要增加门店信息
            FineStore fineStore = addOrganizationDTO.getFineStore();
            if(fineStore!=null){
                FineStoreExample fineStoreExample=new FineStoreExample();
                fineStoreExample.createCriteria().andIdEqualTo(fineStore.getId());
                fineStoreMapper.updateByExampleSelective(fineStore,fineStoreExample);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResultModel.error("更新失败！");
        }
        return ResultModel.ok();
    }

    /**
     * 获取员工所属的组织信息
     *
     * @param id
     * @return
     */
    @Override
    public Map getOrganizationByStaffId(Integer id) {
        Map<String,String> map=new HashMap<>();
        FineOrg fineOrg = fineOrgMapper.selectOrgByStaffId(id);
        map.put("id",fineOrg.getId().toString());
        map.put("shortName",fineOrg.getShortName());
        return map;
    }
}
