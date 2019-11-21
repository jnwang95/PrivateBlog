package com.wjn.controller;

import com.wjn.bean.validator.AlterPassword;
import com.wjn.model.admin.User;
import com.wjn.service.AdminIndexService;
import com.wjn.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * The type Admin index controller.
 *
 * @auther WJN
 * @date 2019 /9/17 15:19
 * @describetion 首页控制器
 */
@Api(value = "后台主页接口")
@RestController
@RequestMapping("index")
public class AdminIndexController {

    @Autowired
    private AdminIndexService adminIndexService;

    /**
     * 获取用户名使用
     *
     * @return json result
     */
    @GetMapping("username")
    @ApiOperation(value = "用户名")
    @RequiresRoles(value = "common")
    public JsonResult username(){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
       return JsonResult.success(user.getName());
    }

    /**
     * 修改密码
     *
     * @param alterPassword the alter password
     * @return json result
     */
    @PostMapping("alterPassword")
    @ApiOperation(value = "修改密码")
    @RequiresRoles(value = "host")
    public JsonResult alterPassword(AlterPassword alterPassword){
        adminIndexService.alterPassword(alterPassword);
        return JsonResult.success("修改成功");
    }
}
