package com.wjn.common;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.TypeUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

/**
 * @description:
 * @author: jnWang
 * @create: 2019-12-18 14:03
 */
@Component
public class Test {
@Autowired
BeanFactory factory;

    public static void main(String[] args) throws IOException {
        Resource resource = new ClassPathResource("bean.xml");
        InputStream inputStream = resource.getInputStream();
//        QrCodeUtil.generate("徐迎庆你好？", 300, 300, FileUtil.file("d:/qrcode.jpg"));

//        boolean empty = Validator.isEmail("414185261@qq.com");
//        System.out.println(empty);
//        boolean mobile = Validator.isMobile("123456789");
//        System.out.println(mobile);
//        Console.log(mobile);

    }
    public void test(){
        factory.getBean("");
    }

}
