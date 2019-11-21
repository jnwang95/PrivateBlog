package com.wjn.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.wjn.bean.validator.LoginUser;
import com.wjn.utils.PasswordUtil;
import com.wjn.util.ShiroUtil;
import com.wjn.utils.HttpContextUtils;
import com.wjn.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

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
    private String CAPTCHA_KEY = "CAPTCHA_KEY";

    @Autowired
    private PasswordUtil passwordUtil;

    /**
     * @param loginUser the login user
     * @param result    the result
     * @return json result
     */
    @PostMapping("login")
    @ApiOperation(value = "用户登录")
    public JsonResult login(@Valid LoginUser loginUser, BindingResult result){
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        HttpServletResponse response = HttpContextUtils.getHttpServletResponse();
        //验证账号密码的合法性
        if(result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (ObjectError error : result.getAllErrors()) {
                sb.append(error.getDefaultMessage()).append("\r\n");
            }
            return JsonResult.failMessage(sb.toString());
        }
        //验证验证码
//        String kaptcha = ShiroUtil.getKaptcha(CAPTCHA_KEY);
//        if(!loginUser.getVerify().equalsIgnoreCase(kaptcha)){
//            return JsonResult.failMessage("验证码不正确");
//        }

        //获取加密的密码
        String encryptPassword = passwordUtil.encrypt(loginUser.getPassword());
        //登录
        ShiroUtil.login(loginUser.getUsername(),encryptPassword);
        //4.获取sessionId
        String sessionId = ShiroUtil.getSessionId();
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
    public void captcha(){
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        HttpServletResponse response = HttpContextUtils.getHttpServletResponse();
        //生成验证码图片
        CircleCaptcha lineCaptcha = CaptchaUtil.createCircleCaptcha(200, 100);
        try {
            ShiroUtil.setSessionAttribute(CAPTCHA_KEY, lineCaptcha.getCode());
            response.setContentType("image/png");//告诉浏览器输出内容为图片
            response.setHeader("Pragma", "No-cache");//禁止浏览器缓存
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            lineCaptcha.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
