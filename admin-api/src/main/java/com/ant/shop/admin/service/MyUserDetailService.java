package com.ant.shop.admin.service;


import com.ant.shop.asorm.mapper.FineStaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 〈自定义UserDetailService〉
 *
 * @author Curise
 * @create 2018/12/13
 * @since 1.0.0
 */
@Service("userDetailService")
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private FineStaffMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        return null;
    }

}

