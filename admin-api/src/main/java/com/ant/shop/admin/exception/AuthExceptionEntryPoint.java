package com.ant.shop.admin.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:aijiaxiang
 * Date:2019/7/22
 * Description:token异常信息重写
 */
@Component
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint
{

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws ServletException {
        Map<String, Object> map = new HashMap<String, Object>();
        Throwable cause = authException.getCause();

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            if(cause instanceof InvalidTokenException) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", 401);
                jsonObject.put("message","token失效");
                String myResponse = JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
                response.getWriter().write(myResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
