package com.ant.shop.asauth.service;



import com.ant.shop.asorm.entity.auth.Admin;
import com.ant.shop.asorm.mapper.auth.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

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
    private AdminMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        Admin admin = adminMapper.findByMobileAndEmail(loginName);
        if (admin == null) {
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
//        for (Role role : user.getRoles()) {
//            //角色必须是ROLE_开头，可以在数据库中设置
//            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
//            grantedAuthorities.add(grantedAuthority);
//            //获取权限
//            for (Permission permission : role.getPermissions()) {
//                GrantedAuthority authority = new SimpleGrantedAuthority(permission.getUri());
//                grantedAuthorities.add(authority);
//            }
//        }
        User user = new User(admin.getId().toString(), admin.getPassword(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
        return user;
    }

}

