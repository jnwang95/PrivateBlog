package com.wjn.config;

import com.wjn.mapper.UserMapper;
import com.wjn.model.admin.User;
import com.wjn.model.admin.UserRole;
import com.wjn.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.relation.Role;
import java.util.List;
import java.util.Set;

/**
 * @description: 自定义realm
 * @author: jnWang
 * @create: 2019-11-19 15:09
 */
public class MyRealm extends AuthorizingRealm {


    @Override
    public void setName(String name){
        super.setName("MyRealm");
    }


    @Autowired
    private UserService userService;

    /**
     * 构造授权方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //1.获取认证的用户数据
        User user = (User) principalCollection.getPrimaryPrincipal();
        //2.构造认证数据
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Integer roleId = user.getRoleId();
        List<Integer> permissionIds = userService.getPermissionIdsByRoleId(roleId);
        //添加角色
        info.addRole(roleId.toString());
        for (Integer permissionId : permissionIds) {
            //添加权限信息
            info.addStringPermission(permissionId.toString());
        }
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
        //1.获取登录的upToken
        UsernamePasswordToken upToken = (UsernamePasswordToken)authenticationToken;
        //2.获取输入的用户名密码
        String username = upToken.getUsername();
        String password = new String(upToken.getPassword());
        //3.数据库查询用户
        User user = userService.findByName(username);
        //4.用户存在并且密码匹配存储用户数据
        if(user != null && user.getPassword().equals(password)) {
           return new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
        }else {
        //返回null会抛出异常，表明用户不存在或密码不匹配
        return null;
        }
    }
}
