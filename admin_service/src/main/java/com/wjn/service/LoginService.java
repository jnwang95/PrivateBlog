package com.wjn.service;

/**
 * @auther WJN
 * @date 2019/10/12 0012 上午 12:17
 * @describe 登录服务层
 */
public interface LoginService {
    /**
     * 验证登录账号和密码的正确性
     * @param username
     * @param password
     * @return
     */
    boolean JudgeUser(String username, String password);
}
