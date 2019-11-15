package com.wjn.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.wjn.bean.validator.LoginUser;
import com.wjn.service.LoginService;
import com.wjn.utils.ConfigConstant;
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
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

/**
 * @auther WJN
 * @date 2019/9/2 13:18
 * @describetion 登陆控制器
 */
@RestController
@Api(value = "登录接口", description = "登录接口")
public class LoginController {
    private String CAPTCHA_KEY = "CAPTCHA_KEY";

    @Autowired
    private LoginService loginService;
    @Autowired
    private ConfigConstant configConstant;

    /**
     * TODO 注意：自定义头信息，如果不加response.setHeader("Access-Control-Expose-Headers", "token")，前端是获取不到的
     *
     * @return
     */
//    @Log("账号登录")
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
        //验证验证码正确性
        HttpSession session = request.getSession();
        Object captcha_key = session.getAttribute(CAPTCHA_KEY);
        if(!loginUser.getVerify().equalsIgnoreCase(captcha_key + "")){
            return JsonResult.failMessage("验证码不正确");
        }
        session.removeAttribute(CAPTCHA_KEY);
        //验证账号密码的正确性
        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, configConstant.getKeyStr().getBytes());
        //加密为16进制表示 判断
        String encryptHex = aes.encryptHex(loginUser.getPassword());
         boolean trueOrFalse = loginService.JudgeUser(loginUser.getUsername(),encryptHex);
         //如果账号密码错误则返回
         if(!trueOrFalse){
             return JsonResult.failMessage("账号或者密码错误");
         }
        //设置http头信息
        String token = UUID.randomUUID().toString();
        response.setHeader(configConstant.getToken(),token);
        response.setHeader("Access-Control-Expose-Headers", configConstant.getToken());
        //设置session信息，用来验证客户
        session.setAttribute(configConstant.getToken(),token);
        session.setMaxInactiveInterval(60*60*24);
        return JsonResult.success();
    }

    /**
     * 用户推出，消除session
     * @return
     */
    @GetMapping("logout")
    @ApiOperation(value = "账号退出")
    public JsonResult logout(){
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        request.getSession().removeAttribute(configConstant.getToken());
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
            HttpSession session = request.getSession();
            session.setAttribute(CAPTCHA_KEY, lineCaptcha.getCode());
            session.setMaxInactiveInterval(1000);
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
     * @param args
     */
    public static void main(String[] args) {
        byte[] key = "asdfghjsdfghjhsl".getBytes();
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        //加密为16进制表示 判断
        String encryptHex = aes.encryptHex("admin123");
        System.out.println(encryptHex);
    }

}
