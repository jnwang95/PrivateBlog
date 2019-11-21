package com.wjn.common;

import com.wjn.config.ShiroState;
import com.wjn.utils.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: shiro公共错误
 * @author: jnWang
 * @create: 2019-11-20 09:23
 */
@RestController
public class BaseErrorController {

    /**
     * 公共错误
     * @return
     */
    @RequestMapping(value="401")
    public JsonResult autherror(int code){
       if(code == ShiroState.NO_LOGIN.getCode()) return JsonResult.failMessage(ShiroState.NO_LOGIN.getDesc());
        return JsonResult.failMessage(ShiroState.NO_PERMISSION.getDesc());
    }
}
