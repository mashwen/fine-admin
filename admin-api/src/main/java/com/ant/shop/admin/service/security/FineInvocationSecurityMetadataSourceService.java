package com.ant.shop.admin.service.security;

import com.ant.shop.admin.service.impl.ResourceServiceImpl;
import com.ant.shop.asorm.entity.FineResource;
import com.ant.shop.asorm.mapper.FineResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 权限加载和筛选
 *
 * @author venky
 * @version 2019年6月28日
 * @see FineInvocationSecurityMetadataSourceService
 * @since 1.0
 */
@Service
public class FineInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

//    @Autowired
//    private FineResourceMapper fineResourceMapper;
//    @Autowired
//    private ResourceServiceImpl resourceService;
    @Autowired
    @Lazy
    private RedisTemplate<String, Object> redisTemplate;


    /*private HashMap<String, Collection<ConfigAttribute>> map = null;

    public void loadResourceDefine() {
        map = new HashMap<>();
        Collection<ConfigAttribute> array;
        ConfigAttribute cfg;
        List<FineResource> permissions = fineResourceMapper.findAll();
        for (FineResource permission : permissions) {
            array = new ArrayList<>();
            cfg = new SecurityConfig(permission.getName());
            array.add(cfg);
            map.put(permission.getUrl(), array);
        }
    }*/

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();
        Map<Object, Object> map = ops.entries("allResource");

        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();

        AntPathRequestMatcher matcher;
        String resUrl;
        for (Object s : map.keySet()) {
            resUrl = s.toString();
            matcher = new AntPathRequestMatcher(resUrl);
            if (matcher.matches(request)) {
                return (Collection<ConfigAttribute>)map.get(resUrl);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
