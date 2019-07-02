package com.ant.shop.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.ant.shop.admin.service.FineAdminLogService;
import com.ant.shop.asorm.entity.FineAdminLog;
import com.ant.shop.asorm.entity.FineStaff;
import com.ant.shop.asorm.mapper.FineAdminLogMapper;
import com.ant.shop.asorm.model.LogModel;
import com.ant.shop.asorm.model.PageDTO;
import com.ant.shop.asorm.model.PageListResp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import enums.LogModelEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;
import utils.DateUtil;
import utils.JsonUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author:aijiaxiang
 * Date:2019/6/24
 * Description:
 */
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
        List<FineAdminLog> list = fineAdminLogMapper.selectByCreatorId(id);
        List<LogModel> logModels = new ArrayList<>();
        PageHelper.startPage(pageNum, pageSize);
        List<FineAdminLog> fineAdminLogs = fineAdminLogMapper.selectByCreatorId(id);
        for (FineAdminLog fineAdminLog:fineAdminLogs) {
            LogModel logModel = new LogModel();
            logModel.setLogId(fineAdminLog.getId().toString());
            logModel.setOperation(fineAdminLog.getOperation());
            logModel.setCreated(DateUtil.valueOf(fineAdminLog.getCreated(),"yyyy-MM-dd HH:mm:ss"));
            logModel.setOperationModule(LogModelEnum.LogOperationModuleEnum.getValueByCode(fineAdminLog.getRefTable()));
            logModels.add(logModel);
        }

        PageInfo<LogModel> pageInfo = new PageInfo<>(logModels);
        PageDTO pageDTO = new PageDTO();
        pageDTO.setCountPerPage(pageInfo.getPageSize());
        pageDTO.setNextPage(pageInfo.getNextPage());
        pageDTO.setPage(pageInfo.getPageNum());
        pageDTO.setPrevPage(pageInfo.getPrePage());
        pageDTO.setTotalCount(list.size());
        pageDTO.setTotalPage(pageInfo.getPages());
        PageListResp pageList = new PageListResp();
        pageList.setList(logModels);
        pageList.setPagination(pageDTO);
        pageList.setCount(list.size());
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
        List<FineAdminLog> list = fineAdminLogMapper.selectAllLogs();
        List<LogModel> logModels = new ArrayList<>();
        PageHelper.startPage(pageNum, pageSize);
        List<FineAdminLog> fineAdminLogs = fineAdminLogMapper.selectAllLogs();
        for (FineAdminLog fineAdminLog:fineAdminLogs) {
            LogModel logModel = new LogModel();
            logModel.setLogId(fineAdminLog.getId().toString());
            logModel.setOperation(fineAdminLog.getOperation());
            logModel.setCreated(DateUtil.valueOf(fineAdminLog.getCreated(),"yyyy-MM-dd HH:mm:ss"));
            logModel.setOperationModule(LogModelEnum.LogOperationModuleEnum.getValueByCode(fineAdminLog.getRefTable()));
            logModels.add(logModel);
        }
        PageInfo<FineAdminLog> pageInfo = new PageInfo<>(fineAdminLogs);
        PageDTO pageDTO = new PageDTO();
        pageDTO.setCountPerPage(pageInfo.getPageSize());
        pageDTO.setNextPage(pageInfo.getNextPage());
        pageDTO.setPage(pageInfo.getPageNum());
        pageDTO.setPrevPage(pageInfo.getPrePage());
        pageDTO.setTotalCount(list.size());
        pageDTO.setTotalPage(pageInfo.getPages());
        PageListResp pageList = new PageListResp();
        pageList.setList(logModels);
        pageList.setPagination(pageDTO);
        pageList.setCount(list.size());
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
    public PageListResp selectLogsByDate(Integer pageNum, Integer pageSize,Date startTime, Date endTime) {
        List<FineAdminLog> list = fineAdminLogMapper.selectByDate(startTime, endTime);
        List<LogModel> logModels = new ArrayList<>();
        PageHelper.startPage(pageNum, pageSize);
        List<FineAdminLog> fineAdminLogs = fineAdminLogMapper.selectByDate(startTime, endTime);
        for (FineAdminLog fineAdminLog:fineAdminLogs) {
            LogModel logModel = new LogModel();
            logModel.setLogId(fineAdminLog.getId().toString());
            logModel.setOperation(fineAdminLog.getOperation());
            logModel.setCreated(DateUtil.valueOf(fineAdminLog.getCreated(),"yyyy-MM-dd HH:mm:ss"));
            logModel.setOperationModule(LogModelEnum.LogOperationModuleEnum.getValueByCode(fineAdminLog.getRefTable()));
            logModels.add(logModel);
        }
        PageInfo<FineAdminLog> pageInfo = new PageInfo<>(fineAdminLogs);
        PageDTO pageDTO = new PageDTO();
        pageDTO.setCountPerPage(pageInfo.getPageSize());
        pageDTO.setNextPage(pageInfo.getNextPage());
        pageDTO.setPage(pageInfo.getPageNum());
        pageDTO.setPrevPage(pageInfo.getPrePage());
        pageDTO.setTotalCount(list.size());
        pageDTO.setTotalPage(pageInfo.getPages());
        PageListResp pageList = new PageListResp();
        pageList.setList(logModels);
        pageList.setPagination(pageDTO);
        pageList.setCount(list.size());
        return pageList;
    }

    /**
     * 日志详情
     * @param id
     * @return
     */
    public FineAdminLog selectByPrimaryKey(Integer id){
        FineAdminLog fineAdminLog = fineAdminLogMapper.selectByPrimaryKey(id);
        return fineAdminLog;
    }
}
