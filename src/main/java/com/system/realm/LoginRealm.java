package com.system.realm;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.system.po.Role;
import com.system.po.User;
import com.system.service.RoleService;
import com.system.service.UserService;

@Component
public class LoginRealm extends AuthorizingRealm{
    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    /**
     * 获取身份信息，我们可以在这个方法中，从数据库获取该用户的权限和角色信息
     *     当调用权限验证时，就会调用此方法
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) getAvailablePrincipal(principalCollection);
        Role role = null;
        try {
        	User currentUser = userService.selectOne(new EntityWrapper<User>()
    				.eq("user_name", userName));
            //获取角色对象
            role = roleService.selectById(currentUser.getRoleId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //通过用户名从数据库获取权限/角色信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> r = new HashSet<String>();
        if (role != null) {
            r.add(role.getRoleName());
            info.setRoles(r);
        }

        return info;
    }

    /**
     * 在这个方法中，进行身份验证
     *         login时调用
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //用户名
        String userName = (String) token.getPrincipal();
        //密码
        String password = new String((char[])token.getCredentials());

        User user = null;
        try {
            user = userService.selectOne(new EntityWrapper<User>()
    				.eq("user_name", userName));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (user == null) {
            //没有该用户名
            throw new UnknownAccountException();
        } else if (!password.equals(user.getPassword())) {
            //密码错误
            throw new IncorrectCredentialsException();
        }

        //身份验证通过,返回一个身份信息
        AuthenticationInfo aInfo = new SimpleAuthenticationInfo(userName,password,getName());

        return aInfo;
    }
}
