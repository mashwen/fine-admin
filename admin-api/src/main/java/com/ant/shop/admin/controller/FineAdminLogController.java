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

    /**
     * 根据工作人员查询操作日志
     * @param id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/stafflogs")
    public ResultModel getStaffLogs(@RequestParam(required = true)Integer id,@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                    @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        PageListResp pageListResp = fineAdminLogService.selectByCreatorId(id,pageNum,pageSize);
        Map<String, Object> data = new HashMap<>();
        data.put("pagination",pageListResp.getPagination());
        data.put("stafflogs",pageListResp.getList());

        return ResultModel.ok(data);
    }

    @GetMapping("/alllogs")
    public ResultModel selectAllLogs(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                    @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        PageListResp pageListResp = fineAdminLogService.selectAllLogs(pageNum,pageSize);
        Map<String, Object> data = new HashMap<>();
        data.put("pagination",pageListResp.getPagination());
        data.put("alllogs",pageListResp.getList());

        return ResultModel.ok(data);
    }

    @GetMapping("/logbydate")
    public ResultModel selectLogsByDate(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                        @RequestParam(required = false, defaultValue = "10") Integer pageSize, String startTime, String endTime){
        if (startTime ==null){
            return ResultModel.error("请输入开始时间");
        }
        if (endTime==null){
            return ResultModel.error("请输入结束时间");
        }
        PageListResp pageListResp = null;
        try {
            pageListResp = fineAdminLogService.selectLogsByDate(pageNum, pageSize, startTime, endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Map<String, Object> data = new HashMap<>();
        data.put("pagination",pageListResp.getPagination());
        data.put("logbydate",pageListResp.getList());

        return ResultModel.ok(data);
    }

    @GetMapping("/stafflogsbydate")
    public ResultModel selectStaffLogsByDate(@RequestParam(required = true) Integer id,@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                             @RequestParam(required = false, defaultValue = "10") Integer pageSize, String startTime, String endTime){
        if (startTime ==null){
            return ResultModel.error("请输入开始时间");
        }
        if (endTime==null){
            return ResultModel.error("请输入结束时间");
        }
        PageListResp pageListResp = fineAdminLogService.selectStaffLogsByDate(id,pageNum,pageSize,startTime,endTime);
        Map<String, Object> data = new HashMap<>();
        data.put("pagination",pageListResp.getPagination());
        data.put("logbydate",pageListResp.getList());

        return ResultModel.ok(data);
    }

    @GetMapping("/logdetail")
    public String selectByPrimaryKey(Integer id){
        String content = fineAdminLogService.selectByPrimaryKey(id);

        return content;
    }


}
