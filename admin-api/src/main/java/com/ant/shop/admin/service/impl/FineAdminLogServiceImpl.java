package com.ant.shop.admin.service.impl;

import com.ant.shop.admin.service.FineAdminLogService;
import com.ant.shop.asorm.entity.FineAdminLog;
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
     * 记录日志
     * @param fineAdminLog
     * @return
     */
    @Override
    public int insertLog(FineAdminLog fineAdminLog) {

        return fineAdminLogMapper.insert(fineAdminLog);
    }

    /**
     * 根据工作人员查找日志
     * @param id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageListResp selectByCreatorId(Integer id, Integer pageNum, Integer pageSize) {
        List<LogModel> logModels = new ArrayList<>();
        List<FineAdminLog> fineAdminLogs = fineAdminLogMapper.selectByCreatorId(id);
        PageUtil pageUtil = new PageUtil();
        List<FineAdminLog> list = pageUtil.pageUtil(fineAdminLogs, pageNum, pageSize);

        for (FineAdminLog fineAdminLog:list) {
            LogModel logModel = new LogModel();
            logModel.setLogId(fineAdminLog.getId().toString());
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

    /**
     * 查询系统所有日志
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageListResp selectAllLogs(Integer pageNum,Integer pageSize) {
        List<LogModel> logModels = new ArrayList<>();

        List<FineAdminLog> fineAdminLogs = fineAdminLogMapper.selectAllLogs();
        PageUtil pageUtil = new PageUtil();
        List<FineAdminLog> list = pageUtil.pageUtil(fineAdminLogs, pageNum, pageSize);


        for (FineAdminLog fineAdminLog:list) {
            LogModel logModel = new LogModel();
            logModel.setLogId(fineAdminLog.getId().toString());
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

    /**
     * 根据日期查询日志
     * @param pageNum
     * @param pageSize
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public PageListResp selectLogsByDate(Integer pageNum, Integer pageSize,String startTime, String endTime){

        Date startDate = null;
        Date endDate = null;
        try {
            startDate = DateUtil.convertStringToDateFull(startTime);
            endDate = DateUtil.convertStringToDateFull(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<LogModel> logModels = new ArrayList<>();
        List<FineAdminLog> fineAdminLogs = fineAdminLogMapper.selectByDate(startDate, endDate);
        PageUtil pageUtil = new PageUtil();
        List<FineAdminLog> list = pageUtil.pageUtil(fineAdminLogs, pageNum, pageSize);

        for (FineAdminLog fineAdminLog:list) {
            LogModel logModel = new LogModel();
            logModel.setLogId(fineAdminLog.getId().toString());
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

    @Override
    public PageListResp selectStaffLogsByDate(Integer id,Integer pageNum,Integer pageSize,String startTime, String endTime) {
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = DateUtil.convertStringToDateFull(startTime);
            endDate = DateUtil.convertStringToDateFull(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<LogModel> logModels = new ArrayList<>();
        List<FineAdminLog> fineAdminLogs = fineAdminLogMapper.selectStaffLogsByDate(id,startDate, endDate);
        PageUtil pageUtil = new PageUtil();
        List<FineAdminLog> list = pageUtil.pageUtil(fineAdminLogs, pageNum, pageSize);

        for (FineAdminLog fineAdminLog:list) {
            LogModel logModel = new LogModel();
            logModel.setLogId(fineAdminLog.getId().toString());
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
}
