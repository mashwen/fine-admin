package com.ant.shop.asorm.model;

import lombok.Data;

/**
 * Author:aijiaxiang
 * Date:2019/6/26
 * Description:
 */
@Data
public class LogModel {
    private String logId;
    private String operationModule;
    private String operation;
    private String created;
    private String createdBy;
}
