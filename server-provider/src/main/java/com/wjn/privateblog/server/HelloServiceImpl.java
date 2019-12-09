package com.wjn.privateblog.server;

import com.alibaba.dubbo.config.annotation.Service;
import com.wjn.api.HelloService;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: jnWang
 * @create: 2019-12-05 15:57
 */
@Service(interfaceClass = HelloService.class)
@Component
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String message) {
        return "hello," + message;
    }
}
