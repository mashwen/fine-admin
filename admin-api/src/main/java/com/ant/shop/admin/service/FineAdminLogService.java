package com.ant.shop.admin.service;

import com.ant.shop.asorm.entity.FineAdminLog;
import com.ant.shop.asorm.model.PageListResp;

import java.text.ParseException;
import java.util.Date;

/**
 * Author:aijiaxiang
 * Date:2019/6/24
 * Description:
 */
public interface FineAdminLogService {

    int insertLog(FineAdminLog fineAdminLog);

    String selectByPrimaryKey(Integer id);

    PageListResp selectLogs(Integer id,String startTime,String endTime,Integer pageNum,Integer pageSize);
}
