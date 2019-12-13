package com.wjn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @description:启动类
 * @author: jnWang
 * @create: 2019-11-15 17:17
 */
@Slf4j
@SpringBootApplication
@MapperScan({"com.wjn.mapper"})
public class AppRunWebpage {

    public static void main(String[] args) {
        SpringApplication.run(AppRunWebpage.class,args);
        log.info(">>>>前台系统启动成功<<<<");
    }
}
