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
    private FineDistrictMapper fineDistrictMapper;
    @Autowired
    private FineAreaMapper fineAreaMapper;
    @Autowired
    private FineDistrictAreaMapper fineDistrictAreaMapper;
    @Autowired
    private FineAdminFieldDataMapper fineAdminFieldDataMapper;
    @Autowired
    private FineAdminFieldMapper fineAdminFieldMapper;
    @Autowired
    private FineStoreMapper fineStoreMapper;
    @Autowired
    private FineStaffOrgRoleMapper fineStaffOrgRoleMapper;
    /**
     * 获取组织列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageListResp<OrganizationDTO> getOrganization(Integer pageNum, Integer pageSize) {
        //查询全部
        List<OrganizationDTO> list = fineOrgMapper.selectAll();
        //设置分页
        PageHelper.startPage(pageNum,pageSize);
        //分页列表
        List<OrganizationDTO> fineOrgList = fineOrgMapper.selectAll();

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
        pageList.setCount(list.size());

        return pageList;
    }

    /**
     * 启用/禁用 组织
     * @param id
     * @param enabled
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setOrganizationEnabled(Integer id, Boolean enabled) {
        //修改的参数
        FineOrg fineOrg = new FineOrg();
        fineOrg.setIsEnabled(enabled);
        //条件
        FineOrgExample fineOrgExample=new FineOrgExample();
        fineOrgExample.createCriteria().andIdEqualTo(id);
        //执行sql
        fineOrgMapper.updateByExampleSelective(fineOrg,fineOrgExample);
    }

    /**
     * 删除组织
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor =Exception.class)
    public ResultModel deleteOrganizationById(Integer id) {
        //删除前先判断该组织是否启用状态，如果不是启用状态，则可以删除
        FineOrg fineOrg = fineOrgMapper.selectByPrimaryKey(id);
        if(fineOrg.getIsEnabled()){
            return ResultModel.error("0","启用状态下的组织不可以被删除!");
        }
        //1、先删除组织
        fineOrgMapper.deleteByPrimaryKey(id);
        //2、删除行政区域
        FineOrgDistrict fineOrgDistrict = fineOrgDistrictMapper.selectByOrgId(id);
        fineDistrictMapper.deleteByPrimaryKey(fineOrgDistrict.getDistrictId());
        //3、删除业务区域
        FineDistrictAreaExample fineDistrictAreaExample=new FineDistrictAreaExample();
        fineDistrictAreaExample.createCriteria().andDistrictIdEqualTo(id);
        List<FineDistrictAreaKey> fineDistrictAreaKeys = fineDistrictAreaMapper.selectByExample(fineDistrictAreaExample);
        for (FineDistrictAreaKey fineDistrictAreaKey : fineDistrictAreaKeys) {
            fineAreaMapper.deleteByPrimaryKey(fineDistrictAreaKey.getAreaId());
        }
        //4、删除业务区域行政区域关系表
        fineDistrictAreaMapper.deleteByExample(fineDistrictAreaExample);
        //5、删除组织行政区域
        FineOrgDistrictExample fineOrgDistrictExample=new FineOrgDistrictExample();
        fineOrgDistrictExample.createCriteria().andOrgIdEqualTo(id);
        fineOrgDistrictMapper.deleteByExample(fineOrgDistrictExample);
        //6、删除自定义信息
        List<AddOrganizationDTO.FineAdminFields> fineAdminFields = fineAdminFieldMapper.selectByRefId(id);
        FineAdminFieldDataExample fineAdminFieldDataExample;
        for (AddOrganizationDTO.FineAdminFields fineAdminField : fineAdminFields) {
            fineAdminFieldDataExample =new FineAdminFieldDataExample();
            fineAdminFieldDataExample.createCriteria().andFieldIdEqualTo(fineAdminField.getFineAdminFieldData().getFieldId())
            .andRefIdEqualTo(fineAdminField.getFineAdminFieldData().getRefId());
            fineAdminFieldDataMapper.deleteByExample(fineAdminFieldDataExample);
        }
        //7、删除门店
        FineStoreExample fineStoreExample=new FineStoreExample();
        fineStoreExample.createCriteria().andOrgIdEqualTo(id);
        fineStoreMapper.deleteByExample(fineStoreExample);

        //8、删除员工组织角色关系
        FineStaffOrgRoleExample fineStaffOrgRoleExample=new FineStaffOrgRoleExample();
        fineStaffOrgRoleExample.createCriteria().andOrgIdEqualTo(id);
        fineStaffOrgRoleMapper.deleteByExample(fineStaffOrgRoleExample);

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
    public List<OrganizationDTO> getOrganizationByKeyword(Byte type, Boolean enabled, String keyword) {

        OrganizationDTO organizationDTO=new OrganizationDTO();
        organizationDTO.setType(type);
        organizationDTO.setIsEnabled(enabled);
        if(StringUtils.isInteger(keyword)){
            log.info("if");
            organizationDTO.setCode(keyword);
        }else{
            log.info("else");
            organizationDTO.setName(keyword);
        }


        List<OrganizationDTO> organizationDTOS = fineOrgMapper.selectByKeyword(organizationDTO);

        return organizationDTOS;
    }

    /**
     * 新增组织
     *
     * @param addOrganizationDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setOrganization(AddOrganizationDTO addOrganizationDTO) {
        //1、添加组织基本信息
        FineOrg fineOrg=new FineOrg();
        BeanUtils.copyProperties(addOrganizationDTO,fineOrg);
        fineOrg.setCreated(new Date());
        fineOrgMapper.insert(fineOrg);
        //2、添加行政区域
        FineDistrict fineDistrict=new FineDistrict();
        BeanUtils.copyProperties(addOrganizationDTO.getFineDistrict(),fineDistrict);
        fineDistrict.setCreated(new Date());
        fineDistrictMapper.insert(fineDistrict);
        //3、添加组织行政区域
        FineOrgDistrict fineOrgDistrict=new FineOrgDistrict();
        BeanUtils.copyProperties(addOrganizationDTO.getOrgDistrict(),fineOrgDistrict);
        fineOrgDistrict.setOrgId(fineOrg.getId());
        fineOrgDistrict.setCreated(new Date());
        fineOrgDistrict.setDistrictId(fineDistrict.getId());
        fineOrgDistrictMapper.insert(fineOrgDistrict);
        //4、添加业务区域
        FineDistrictAreaKey fineDistrictAreaKey=new FineDistrictAreaKey();
        List<FineArea> fineAreaList = addOrganizationDTO.getFineAreaList();
        for (FineArea area : fineAreaList) {
            area.setCreated(new Date());
            fineAreaMapper.insert(area);
            log.info("id:"+area.getId());
            //5、添加业务区域行政区域关系
            fineDistrictAreaKey.setAreaId(area.getId());
            fineDistrictAreaKey.setDistrictId(fineDistrict.getId());
            fineDistrictAreaMapper.insert(fineDistrictAreaKey);
        }

        //6、添加更多组织信息
        List<AddOrganizationDTO.FineAdminFields> fineAdminFieldDataList = addOrganizationDTO.getFineAdminFieldDataList();

        for (AddOrganizationDTO.FineAdminFields fineAdminFields : fineAdminFieldDataList) {
            log.info("field的id:"+fineAdminFields.getFineAdminField().getId());
            fineAdminFields.getFineAdminFieldData().setRefId(fineOrg.getId());
            fineAdminFields.getFineAdminFieldData().setCreated(new Date());
            fineAdminFields.getFineAdminFieldData().setFieldId(fineAdminFields.getFineAdminField().getId());
            fineAdminFieldDataMapper.insert(fineAdminFields.getFineAdminFieldData());

        }
        //7、如果是门店的话需要增加门店信息
        FineStore fineStore = addOrganizationDTO.getFineStore();
        if(fineStore!=null){
            fineStore.setOrgId(fineOrg.getId());
            fineStore.setCreated(new Date());
            fineStoreMapper.insert(fineStore);
        }

    }

    /**
     * 组织详情
     * @param id
     * @return
     */
    @Override
    public AddOrganizationDTO getOrganizationById(Integer id) {
        AddOrganizationDTO addOrganizationDTO=new AddOrganizationDTO();

        //组织基本信息
        FineOrg fineOrg = fineOrgMapper.selectByPrimaryKey(id);
        if(fineOrg==null){
            return addOrganizationDTO;
        }
        BeanUtils.copyProperties(fineOrg,addOrganizationDTO);
        //组织行政区域信息
        FineOrgDistrict fineOrgDistrict = fineOrgDistrictMapper.selectByOrgId(id);

        //行政区域信息
        FineDistrict fineDistrict = fineDistrictMapper.selectByPrimaryKey(fineOrgDistrict.getDistrictId());

        //业务区域信息
        List<FineArea> fineAreas = fineAreaMapper.selectByDistrictId(fineDistrict.getId());

        //更多信息
        List<AddOrganizationDTO.FineAdminFields> fineAdminFields = fineAdminFieldMapper.selectByRefId(id);

        //店铺信息
        FineStoreExample fineStoreExample=new FineStoreExample();
        fineStoreExample.createCriteria().andOrgIdEqualTo(id);
        List<FineStore> fineStores = fineStoreMapper.selectByExample(fineStoreExample);


        //计算员工人数
        if(fineStores!=null && fineStores.size()!=0){
            Integer staffCount = fineStaffOrgRoleMapper.countByOrgId(id);
            addOrganizationDTO.setStaffCount(staffCount);
        }
        addOrganizationDTO.setOrgDistrict(fineOrgDistrict);
        addOrganizationDTO.setFineDistrict(fineDistrict);
        addOrganizationDTO.setFineAreaList(fineAreas);
        addOrganizationDTO.setFineStore(fineStores.size()==0?null:fineStores.get(0));
        addOrganizationDTO.setFineAdminFieldDataList(fineAdminFields);
        return addOrganizationDTO;
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
