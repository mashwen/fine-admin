package com.ant.shop.admin.service;


import com.ant.shop.asorm.entity.FineStaff;
import com.ant.shop.asorm.mapper.FineStaffMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import utils.BryptUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 〈自定义UserDetailService〉
 *
 * @author Curise
 * @create 2018/12/13
 * @since 1.0.0
 */
@Slf4j
@Service("userDetailService")
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private FineStaffMapper fineStaffMapper;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        FineStaff fineStaff = fineStaffMapper.findByMobileAndEmail(loginName);
        log.info("finestaff====="+fineStaff);
        //log.info("======="+ BryptUtils.brypt("admin_client_secret_antShop"));
        if (fineStaff==null){
            throw new UsernameNotFoundException(loginName);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // 可用性 :true:可用 false:不可用
        boolean enabled = true;
        // 过期性 :true:没过期 false:过期
        boolean accountNonExpired = true;
        // 有效性 :true:凭证有效 false:凭证无效
        boolean credentialsNonExpired = true;
        // 锁定性 :true:未锁定 false:已锁定
        boolean accountNonLocked = true;
        User user = new User(fineStaff.getId().toString(),fineStaff.getPassword(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
        return user;
    }

}

