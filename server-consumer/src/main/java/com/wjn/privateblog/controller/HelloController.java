package com.wjn.privateblog.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wjn.api.HelloService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: jnWang
 * @create: 2019-12-05 16:03
 */
@RestController
public class HelloController {

    @Reference
    private HelloService helloService;

    @GetMapping("hello/{message}")
    public String hello(@PathVariable("message") String message) {
        return this.helloService.hello(message);
    }

    @GetMapping("hello")
    public void hello1() {
        System.out.println(1253);
    }
}
