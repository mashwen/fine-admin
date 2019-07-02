package com.ant.shop.admin.service;

import com.ant.shop.asorm.entity.FineAdminLog;
import com.ant.shop.asorm.model.PageListResp;

import java.util.Date;

/**
 * Author:aijiaxiang
 * Date:2019/6/24
 * Description:
 */
public interface FineAdminLogService {

    int insertLog(FineAdminLog fineAdminLog);

    PageListResp selectByCreatorId(Integer id, Integer pageNum, Integer pageSize);

    PageListResp selectAllLogs(Integer pageNum, Integer pageSize);

    PageListResp selectLogsByDate(Integer pageNum, Integer pageSize,Date startTime, Date endTime);

    FineAdminLog selectByPrimaryKey(Integer id);
}
