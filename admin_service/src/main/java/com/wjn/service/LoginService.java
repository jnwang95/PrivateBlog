package com.wjn.service;

import com.wjn.bean.validator.LoginUser;
import org.springframework.validation.BindingResult;

import java.io.IOException;
import java.util.Map;

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

    /**
     * 账户登录
     * @param loginUser
     * @param result
     * @return
     */
    String login(LoginUser loginUser, BindingResult result);

    /**
     * 获取验证码
     * @return
     */
    Map<String, String> captcha() throws IOException;
}
