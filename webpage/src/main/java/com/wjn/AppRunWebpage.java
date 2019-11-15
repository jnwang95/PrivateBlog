package com.wjn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @description:启动类
 * @author: jnWang
 * @create: 2019-11-15 17:17
 */
@SpringBootApplication
@MapperScan({"com.wjn.mapper"})
public class AppRunWebpage {

    public static void main(String[] args) {
        SpringApplication.run(AppRunWebpage.class,args);
    }
}
