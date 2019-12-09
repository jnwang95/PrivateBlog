package com.wjn.privateblog;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: jnWang
 * @create: 2019-12-05 16:02
 */
@EnableDubbo
@SpringBootApplication
public class ConsumerApplicaiton {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplicaiton.class, args);
    }
}
