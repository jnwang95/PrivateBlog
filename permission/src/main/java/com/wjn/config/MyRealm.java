package com.wjn.config;

import cn.hutool.core.bean.BeanUtil;
import com.wjn.mapper.UserRoleMapper;
import com.wjn.model.admin.User;
import com.wjn.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * @description: 自定义realm
 * @author: jnWang
 * @create: 2019-11-19 15:09
 */

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public void setName(String name){
        super.setName("myRealm");
    }


    /**
     * 构造授权方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //1.获取安全数据
        User result = (User)principalCollection.getPrimaryPrincipal();
        //2.获取权限信息
        Long userId = result.getId();
        List<Integer> roleIds = userService.getRoleIdsByUserId(userId);
        Set<String> roleNames = userService.getRoleNameByRoleIds(roleIds);
        //3.构造权限数据，返回值
        SimpleAuthorizationInfo info = new  SimpleAuthorizationInfo();
        info.setRoles(roleNames);
        return info;
    }

    /**
     * 构造认证方法
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.获取用户的手机号和密码
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String username = upToken.getUsername();
        String password = new String( upToken.getPassword());
        //3.数据库查询用户
        User user = userService.findByName(username);
        //4.用户存在并且密码匹配存储用户数据
        if(!BeanUtil.isEmpty(user) && user.getPassword().equals(password)) {
           return new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
        }else {
            throw new SecurityException("用户名或者密码错误");
        }
    }
}
