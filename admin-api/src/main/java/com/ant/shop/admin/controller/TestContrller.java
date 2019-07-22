package com.ant.shop.admin.controller;


import com.ant.shop.admin.service.DistrictService;
import com.ant.shop.asorm.entity.FineDistrict;
import com.ant.shop.asorm.entity.FineStaff;
import com.ant.shop.asorm.mapper.FineStaffMapper;
import enums.LogModelEnum;
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

    @Autowired
    private DistrictService districtService;

    @GetMapping("/test")
    @ResponseBody
    public Object test() {
        return districtService.test(0);
    }
}