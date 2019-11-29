package com.wjn.controller;

import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.wjn.annotation.LoginLog;
import com.wjn.bean.validator.LoginUser;
import com.wjn.service.LoginService;
import com.wjn.utils.ShiroUtil;
import com.wjn.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

/**
 * The type Login controller.
 *
 * @auther WJN
 * @date 2019 /9/2 13:18
 * @describetion 登陆控制器
 */
@RestController
@Api(value = "登录接口", description = "登录接口")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * @param loginUser the login user
     * @param result    the result
     * @return json result
     */
    @LoginLog
    @PostMapping("login")
    @ApiOperation(value = "用户登录")
    public JsonResult login(@Valid LoginUser loginUser, BindingResult result){
        String sessionId = loginService.login(loginUser,result);
        return JsonResult.success(sessionId);
    }

    /**
     * 用户退出
     * @return json result
     */
    @GetMapping("logout")
    @ApiOperation(value = "账号退出")
    public JsonResult logout(){
        ShiroUtil.logout();
        return JsonResult.success();
    }


    /**
     * 图形验证码,hutool工具包自带的,拿来即用;
     */
    @GetMapping("captcha")
    @ApiOperation(value = "图形验证码")
    public JsonResult captcha() throws IOException {
        Map<String,String> map = loginService.captcha();
        return JsonResult.success(map);
    }

    /**
     * 测试加密解密用
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        byte[] key = "asdfghjsdfghjhsl".getBytes();
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        //加密为16进制表示 判断
        String encryptHex = aes.encryptHex("123456");
        System.out.println(encryptHex);
    }

}
