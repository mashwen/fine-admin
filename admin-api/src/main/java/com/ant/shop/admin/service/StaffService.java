package com.ant.shop.admin.service;

import com.ant.shop.asorm.entity.FineStaff;
import com.ant.shop.asorm.model.StaffModel;
import response.ResultModel;

public interface StaffService {
    /**
     * 添加员工
     * @param staffModel
     * @return
     */
    ResultModel addStaff(StaffModel staffModel);

    /**
     * 查询员工
     * @param fullname
     * @param mobile
     * @param email
     * @param page
     * @param pageSize
     * @return
     */
    ResultModel staffList(String fullname, String mobile, String email, int page, int pageSize);

    /**
     * 员工详情
     * @param id
     * @return
     */
    ResultModel staffDetails(int id);

    /**
     * 修改员工状态
     * @param fineStaff
     * @return
     */
    ResultModel staffStatus(FineStaff fineStaff);
}
