package com.ant.shop.admin.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Author:aijiaxiang
 * Date:2019/7/21
 * Description:
 */
@Slf4j
public class CustomOauthExceptionJacksonSerializer extends StdSerializer<CustomOauthException> {
    public CustomOauthExceptionJacksonSerializer() {
        super(CustomOauthException.class);
    }

    @Override
    public void serialize(CustomOauthException value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        gen.writeStartObject();
        String message = value.getMessage();
        //log.info("getOAuth2ErrorCode:{}",value.getOAuth2ErrorCode());
        //log.info("getLocalizedMessage:{}",value.getLocalizedMessage());
        //log.info("getOAuth2ErrorCode:{}",value.getSummary());
        if("Bad credentials".equals(message)&&"invalid_request".equals(value.getOAuth2ErrorCode())){
            message = "用户名或密码错误";
        }
        gen.writeStringField("code", String.valueOf(value.getHttpErrorCode()));
        gen.writeStringField("message", message);
        //gen.writeStringField("path", request.getServletPath());
        //gen.writeStringField("timestamp", String.valueOf(System.currentTimeMillis()));
        if (value.getAdditionalInformation()!=null) {
            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                gen.writeStringField(key, add);
            }
        }
        gen.writeEndObject();
    }
}

