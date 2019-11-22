package com.wjn.utils;

import com.wjn.model.admin.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @description: Shiro工具类
 * @author: jnWang
 * @create: 2019-11-21 16:13
 */
public class ShiroUtil {

    /**
     * 获取subject
     * @return
     */
    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    /**
     * 退出登录
     */
    public static void logout(){
        Subject subject = getSubject();
        subject.logout();
    }

    public static User getUser(){
        Subject subject = getSubject();
        return (User)subject.getPrincipal();
    }

    public static void login(String username,String encryptPassword){
        UsernamePasswordToken upToken = new UsernamePasswordToken(username,encryptPassword);
        Subject subject = getSubject();
        subject.login(upToken);
    }

    public static String getSessionId(){
         return (String)getSubject().getSession().getId();
    }

    public static Session getSession() {
        return getSubject().getSession();
    }
    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }
}
