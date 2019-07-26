package com.ant.shop.admin.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import response.ResultModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author liuzongqiang
 * @Date 2019/7/25 0025 15:08
 * @Version 1.0
 **/
public class ControllerUtil {
    /**
     * 检验参数是否正确
     * @param bindingResult
     * @return
     */
    public static ResultModel checkParameter(BindingResult bindingResult){
        List<String> message=new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            message.add(fieldError.getDefaultMessage());
        }
        return ResultModel.error(message.toString());
    }
}
