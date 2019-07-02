package com.ant.shop.admin.service;


import com.ant.shop.asorm.entity.FineResource;
import com.ant.shop.asorm.entity.FineStaff;
import com.ant.shop.asorm.mapper.FineResourceMapper;
import com.ant.shop.asorm.mapper.FineStaffMapper;
import com.ant.shop.asorm.mapper.FineStaffOrgRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * 自定义UserDetailService
 *
 * @author venky
 * @version 2019年6月28日
 * @see MyUserDetailService
 * @since 1.0
 */
@Slf4j
@Service("userDetailService")
public class MyUserDetailService implements UserDetailsService
{
    @Autowired
    private FineStaffMapper fineStaffMapper;

    @Autowired
    private FineStaffOrgRoleMapper fineStaffOrgRoleMapper;

    @Autowired
    private FineResourceMapper fineResourceMapper;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        FineStaff fineStaff = fineStaffMapper.findByMobileAndEmail(loginName);
        log.info("finestaff=====" + fineStaff);
        if (fineStaff == null) {
            throw new UsernameNotFoundException("用户：" + loginName + "不存在");
        }
        List<FineResource> permissions = fineResourceMapper.findAllByStaffId(fineStaff.getId());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (FineResource permission : permissions) {
            if (permission != null && permission.getName() != null) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                grantedAuthorities.add(grantedAuthority);
            }
        }
        // 可用性 :true:可用 false:不可用
        boolean enabled = true;
        // 过期性 :true:没过期 false:过期
        boolean accountNonExpired = true;
        // 有效性 :true:凭证有效 false:凭证无效
        boolean credentialsNonExpired = true;
        // 锁定性 :true:未锁定 false:已锁定
        boolean accountNonLocked = true;
        User user = new User(fineStaff.getId().toString(), fineStaff.getPassword(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
        return user;
    }

}

