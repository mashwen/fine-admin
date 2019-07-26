package com.ant.shop.admin.service;

import com.ant.shop.asorm.entity.FineAdminLog;
import com.ant.shop.asorm.model.PageListResp;

/**
 * Author:aijiaxiang
 * Date:2019/6/24
 * Description:
 */
public interface FineAdminLogService {

    int insertLog(FineAdminLog fineAdminLog);

    int insertServiceLogs(String serviceName,String refTable,String refId,String content,String operation,Integer createdBy);

    String selectByPrimaryKey(Integer id);

    PageListResp selectLogs(Integer id,String serviceName,String startTime,String endTime,Integer pageNum,Integer pageSize);
}
