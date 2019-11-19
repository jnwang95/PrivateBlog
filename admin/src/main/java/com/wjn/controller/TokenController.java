package com.wjn.controller;

import cn.hutool.core.util.StrUtil;
import com.wjn.utils.HttpContextUtils;
import com.wjn.utils.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Token controller.
 *
 * @auther WJN
 * @date 2019 /10/11 16:12
 * @describetion 对存在的页面进行token验证
 */
@RestController()
@RequestMapping("token")
public class TokenController {

    /**
     * 验证token是否为合法身份
     *
     * @return json result
     */
    @GetMapping("verification")
    public JsonResult verificationToken(){
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String token = request.getHeader("token");
        if(token.isEmpty() && token.equals("")){
            return JsonResult.fail();
        }
        HttpSession session = request.getSession();
        String str = (String)session.getAttribute("token");
        if(StrUtil.isEmpty(str)){
            return JsonResult.fail();
        }
        if(str.equals(token)){
            return JsonResult.success();
        }
        return JsonResult.fail();
    }

}
