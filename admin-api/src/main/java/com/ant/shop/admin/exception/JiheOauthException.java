package com.ant.shop.admin.exception;

import com.ant.shop.admin.exception.JiheOauthExceptionJacksonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * Author:aijiaxiang
 * Date:2019/7/21
 * Description:认证登录错误信息重写
 */
@JsonSerialize(using = JiheOauthExceptionJacksonSerializer.class)
public class JiheOauthException extends OAuth2Exception {



    public JiheOauthException(String msg, Throwable t) {
        super(msg, t);

    }

    public JiheOauthException(String msg) {
        super(msg);

    }


}

