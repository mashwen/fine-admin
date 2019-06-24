package com.ant.shop.admin.controller;


import com.ant.shop.asorm.entity.FineStaff;
import com.ant.shop.asorm.mapper.FineStaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class TestContrller {


    @Autowired
    private FineStaffMapper fineStaffMapper;

    @GetMapping("/test")
    @ResponseBody
    public FineStaff test(Principal member) {
        Integer userId = Integer.valueOf(member.getName());
        FineStaff fineStaff = fineStaffMapper.selectByPrimaryKey(userId);
        System.out.println(fineStaff);
        return fineStaff;
    }
}