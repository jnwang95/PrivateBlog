package com.wjn.controller;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @description: 测试控制器
 * @author: jnWang
 * @create: 2019-11-19 09:53
 */
@RestController
public class TestController {

    @GetMapping("testtest111")
    public String test() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        return "你好";
    }
}
