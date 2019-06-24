package com.ant.shop.admin.controller;

import com.ant.shop.admin.service.StaffService;
import com.ant.shop.asorm.entity.FineStaff;
import com.ant.shop.asorm.model.StaffModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import response.ResultModel;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;

@RestController
@RequestMapping("")
public class StaffController {

    @Autowired
    private StaffService staffService;


    //添加员工
    @PostMapping("staff")
    public ResultModel staff(@RequestBody StaffModel staffModel){
        return staffService.addStaff(staffModel);
    }
    //查看员工
    @GetMapping("staffList")
    public ResultModel staffList(HttpServletRequest request,@RequestParam(required = false) String fullname,
                                 @RequestParam(required = false) String mobile,
                                 @RequestParam(required = false) String email,
                                 @RequestParam(required = false, defaultValue = "1") int page,
                                 @RequestParam(required = false, defaultValue = "10") int pageSize){
        int staffId = 1;
        return staffService.staffList(fullname, mobile, email, page, pageSize);
    }
    //查看员工详情
    @GetMapping("staffDetails")
    public ResultModel staffDetails(@RequestParam("id") int id){
        return staffService.staffDetails(id);
    }
    //修改员工状态
    @PostMapping("staffStatus")
    public ResultModel staffStatus(@RequestBody FineStaff fineStaff){
        return staffService.staffStatus(fineStaff);
    }
}
