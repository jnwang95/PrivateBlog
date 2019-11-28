package com.wjn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * The type App run admin.
 *
 * @description: 启动类
 * @author: jnWang
 * @create: 2019 -11-15 16:41
 */
@Slf4j
@SpringBootApplication
@MapperScan({"com.wjn.mapper"})
public class AppRunAdmin {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(AppRunAdmin.class,args);
        log.info("<<<<<<<博客系统启动成功>>>>>>>");
    }

    /*
     * 获取所有注入的Bean
     */
//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//            System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                log.info(beanName);
//            }
//
//        };
//    }

}
