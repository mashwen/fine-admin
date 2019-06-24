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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.StringUtils;

import java.util.Date;
import java.util.List;

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
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor =Exception.class)
    public void deleteOrganizationById(Integer id) {
        fineOrgMapper.deleteByPrimaryKey(id);
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


    }
}
