package com.ant.shop.admin.service.impl;

import com.ant.shop.admin.service.FineAdminLogService;
import com.ant.shop.asorm.entity.FineAdminLog;
import com.ant.shop.asorm.mapper.FineAdminLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author:aijiaxiang
 * Date:2019/6/24
 * Description:
 */
@Service
public class FineAdminLogServiceImpl implements FineAdminLogService {
    @Autowired
    private FineAdminLogMapper fineAdminLogMapper;

    @Override
    public int insertLog(FineAdminLog fineAdminLog) {

        return fineAdminLogMapper.insert(fineAdminLog);
    }
}
