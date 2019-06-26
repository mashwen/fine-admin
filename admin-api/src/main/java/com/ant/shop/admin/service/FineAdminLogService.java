package com.ant.shop.admin.service;

import com.ant.shop.asorm.entity.FineAdminLog;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Author:aijiaxiang
 * Date:2019/6/24
 * Description:
 */
public interface FineAdminLogService {
    int insertLog(FineAdminLog fineAdminLog);
}
