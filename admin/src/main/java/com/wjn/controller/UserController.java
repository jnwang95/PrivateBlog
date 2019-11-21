package com.wjn.controller;

import com.wjn.bean.dto.RegisterUserDto;
import com.wjn.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * The type User controller.
 *
 * @auther WJN
 * @date 2019 /9/1 0001 下午 5:15
 * @describe 用户模块 ，主要进行注册，修改密码，注销账号，封停账号等功能
 */
@Api(tags = "用户接口")
@CrossOrigin
@RestController
@RequestMapping("account")
public class UserController {

    @Autowired
    private UserService service;

    /**
     * 账号注册
     * @param register 注册的用户信息
     * @return 响应信息
     */
    @ApiOperation("注册账号")
    @PostMapping("register")
    @RequiresRoles(value = "common")
    private String register(@RequestBody @Validated RegisterUserDto register, BindingResult bingingresult){
        // 如果验证不通过，错误信息会在BindingResult这个对象中
        if (bingingresult.hasErrors()) {
            return bingingresult.getFieldError().getDefaultMessage();
        }else {
            service.register(register);
        }
        return "success";
    }

}
