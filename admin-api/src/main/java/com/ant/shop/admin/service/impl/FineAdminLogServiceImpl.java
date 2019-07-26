package com.ant.shop.admin.service.impl;

import com.ant.shop.admin.service.FineAdminLogService;
import com.ant.shop.asorm.entity.FineAdminLog;
import com.ant.shop.asorm.entity.FineAdminLogExample;
import com.ant.shop.asorm.mapper.FineAdminLogMapper;
import com.ant.shop.asorm.model.LogModel;
import com.ant.shop.asorm.model.PageDTO;
import com.ant.shop.asorm.model.PageListResp;
import enums.LogModelEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.DateUtil;
import utils.JsonUtil;
import utils.PageUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author:aijiaxiang
 * Date:2019/6/24
 * Description:
 */
@Slf4j
@Service
public class FineAdminLogServiceImpl implements FineAdminLogService {
    @Autowired
    private FineAdminLogMapper fineAdminLogMapper;

    /**
     * 对内接口 记录日志
     * @param fineAdminLog
     * @return
     */
    @Override
    public int insertLog(FineAdminLog fineAdminLog) {

        return fineAdminLogMapper.insert(fineAdminLog);
    }
    /**
     * 对外接口 记录日志
     */
    @Override
    public int insertServiceLogs(String serviceName, String refTable, String refId, String content, String operation, Integer createdBy) {
        FineAdminLog fineAdminLog = new FineAdminLog();
        fineAdminLog.setServiceName(serviceName);
        fineAdminLog.setRefTable(refTable);
        fineAdminLog.setRefId(refId);
        fineAdminLog.setContent(content);
        fineAdminLog.setOperation(operation);
        fineAdminLog.setCreatedBy(createdBy);
        fineAdminLog.setCreated(new Date());
        int sucess = fineAdminLogMapper.insert(fineAdminLog);
        log.info("是否成功：{}",sucess);
        return sucess;
    }

    /**
     * 日志详情
     * @param id
     * @return
     */
    public String selectByPrimaryKey(Integer id){
        FineAdminLog fineAdminLog = fineAdminLogMapper.selectByPrimaryKey(id);
        log.info("content==="+fineAdminLog.getContent());
        String content = JsonUtil.toJson(fineAdminLog.getContent());
        log.info("json==="+content);
        return fineAdminLog.getContent();
    }

    /**
     *
     * @param id 操作人id
     * @param startTime
     * @param endTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageListResp selectLogs(Integer id,String serviceName, String startTime, String endTime, Integer pageNum, Integer pageSize) {
        FineAdminLogExample fineAdminLogExample = new FineAdminLogExample();
        FineAdminLogExample.Criteria criteria = fineAdminLogExample.createCriteria();
        if (id!=null){
            criteria.andCreatedByEqualTo(id);
        }
        if (serviceName!=null){
            criteria.andServiceNameEqualTo(serviceName);
        }
        if (startTime!=null&&endTime!=null){
            try {
                criteria.andCreatedBetween(DateUtil.convertStringToDateFull(startTime),DateUtil.convertStringToDateFull(endTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<LogModel> logModels = new ArrayList<>();
        List<FineAdminLog> fineAdminLogs = fineAdminLogMapper.selectByExample(fineAdminLogExample);
        PageUtil pageUtil = new PageUtil();
        List<FineAdminLog> list = pageUtil.pageUtil(fineAdminLogs, pageNum, pageSize);

        for (FineAdminLog fineAdminLog:list) {
            LogModel logModel = new LogModel();
            logModel.setLogId(fineAdminLog.getId().toString());
            logModel.setServiceName(fineAdminLog.getServiceName());
            logModel.setOperation(fineAdminLog.getOperation());
            logModel.setCreated(DateUtil.valueOf(fineAdminLog.getCreated(),"yyyy-MM-dd HH:mm:ss"));
            logModel.setOperationModule(LogModelEnum.LogOperationModuleEnum.getValueByCode(fineAdminLog.getRefTable()));
            logModel.setCreatedBy(fineAdminLog.getCreatedBy().toString());
            logModels.add(logModel);
        }
        PageDTO pageDTO = new PageDTO();
        pageDTO.setCountPerPage(pageUtil.getCountPerPage());
        pageDTO.setNextPage(pageUtil.getNextPage());
        pageDTO.setPage(pageUtil.getPage());
        pageDTO.setPrevPage(pageUtil.getPrevPage());
        pageDTO.setTotalCount(fineAdminLogs.size());
        pageDTO.setTotalPage(pageUtil.getTotalPage());

        PageListResp pageList = new PageListResp();
        pageList.setList(logModels);
        pageList.setPagination(pageDTO);
        return pageList;
    }
}
