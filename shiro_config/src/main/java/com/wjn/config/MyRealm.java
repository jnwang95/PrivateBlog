package com.wjn.config;

import com.wjn.mapper.UserMapper;
import com.wjn.model.admin.User;
import com.wjn.model.admin.UserRole;
import com.wjn.service.UserService;
import com.wjn.util.JWTUtil;
import com.wjn.util.JwtToken;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 自定义realm
 * @author: jnWang
 * @create: 2019-11-19 15:09
 */
@Component
public class MyRealm extends AuthorizingRealm {

    private final UserService userService;

    @Autowired
    private MyRealm(UserService userService){
        this.userService = userService;
    };

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public void setName(String name){
        super.setName("myRealm");
    }

    /**
     * 必须重写此方法，不然会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 构造授权方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Claims claims = jwtUtil.parseJwt(principalCollection.toString().replaceAll("Bearer ",""));
        String username = claims.getId();
        String password = claims.getSubject();
        //2.构造认证数据
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        List<Integer> permissionIds = userService.getPermissionIdsByUsername(username);
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
        String token = (String)authenticationToken.getCredentials();
        //2.获取输入的用户名密码
        Claims claims = jwtUtil.parseJwt(token.replaceAll("Bearer ", ""));
        String username = claims.getId();
        String password = claims.getSubject();
        //3.数据库查询用户
        User user = userService.findByName(username);
        //4.用户存在并且密码匹配存储用户数据
        if(user != null && user.getPassword().equals(password)) {
           return new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
        }else {
            throw new SecurityException("用户名或者密码错误");
        }
    }
}
