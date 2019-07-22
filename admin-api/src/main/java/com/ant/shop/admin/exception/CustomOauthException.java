package com.ant.shop.admin.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * Author:aijiaxiang
 * Date:2019/7/21
 * Description:认证登录错误信息重写
 */
@JsonSerialize(using = CustomOauthExceptionJacksonSerializer.class)
public class CustomOauthException extends OAuth2Exception {



    public CustomOauthException(String msg, Throwable t) {
        super(msg, t);

    }

    public CustomOauthException(String msg) {
        super(msg);

    }


}

