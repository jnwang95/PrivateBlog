package com.wjn.common;

import com.wjn.utils.JsonResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public JsonResult autherror(){
        return JsonResult.failMessage("未授权");
    }
}
