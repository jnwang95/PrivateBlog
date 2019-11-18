package com.wjn.constant;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @description: 配置文件
 * @author: jnWang
 * @create: 2019-11-18 09:17
 */
@Data
@Component
@PropertySource(value = {"classpath:config/global.properties"})
public class GlobalConfig {

    @Value("${key}")
    private String key;
    @Value("${token}")
    private String token;
    @Value("${aliyun.oss.address}")
    private String ossAddress;
    @Value("${aliyun.oss.end-point}")
    private String endPoint;
    @Value("${aliyun.oss.access-key}")
    private String accessKey;
    @Value("${aliyun.oss.secret-key}")
    private String secretKey;
    @Value("${aliyun.oss.bucket}")
    private String bucket;
}
