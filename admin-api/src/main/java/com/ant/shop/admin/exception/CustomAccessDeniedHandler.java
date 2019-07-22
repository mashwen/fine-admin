package com.ant.shop.admin.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author:aijiaxiang
 * Date:2019/7/22
 * Description:
 */
@Component("customAccessDeniedHandler")
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", 403);
            jsonObject.put("message","用户权限不足");
            String myResponse = JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
            response.getWriter().write(myResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
