package com.ant.shop.admin.controller;

import com.ant.shop.admin.service.FineAdminLogService;
import com.ant.shop.asorm.model.PageListResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import response.ResultModel;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:aijiaxiang
 * Date:2019/6/26
 * Description:
 */
@RestController
@RequestMapping("")
public class FineAdminLogController {
    @Autowired
    private FineAdminLogService fineAdminLogService;

    @GetMapping("/logdetail")
    public String selectByPrimaryKey(Integer id){
        String content = fineAdminLogService.selectByPrimaryKey(id);

        return content;
    }

    @GetMapping("/selectlogs")
    public ResultModel selectLogs(@RequestParam(required = false)Integer id,
                                  @RequestParam(required = false) String startTime,
                                  @RequestParam(required = false) String endTime,
                                  @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        PageListResp pageListResp = fineAdminLogService.selectLogs(id, startTime, endTime, pageNum, pageSize);
        Map<String, Object> data = new HashMap<>();
        data.put("pagination",pageListResp.getPagination());
        data.put("selectlogs",pageListResp.getList());

        return ResultModel.ok(data);
    }
    @PostMapping("/addlogs")
    public ResultModel insertServiceLogs(String serviceName, String refTable, String refId, String content, String operation, Integer createdBy){
        int sucess = fineAdminLogService.insertServiceLogs(serviceName, refTable, refId, content, operation, createdBy);
        if (sucess==1){
            return ResultModel.ok();
        }else {
            return ResultModel.error("插入日志失败");
        }
    }

}
