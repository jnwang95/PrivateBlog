package com.wjn.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 系统参数相关Key
 *
 * @author wjn
 */
@Data
@Component
@PropertySource(value = {"classpath:config/global.properties"})
public class ConfigConstant {

    /** oss处访问图片的 url */
    @Value("${aliyun.oss.address}")
    private String address;
    /** oss的 endpoint */
    @Value("${aliyun.oss.end-point}")
    private String endPoint;
    @Value("${aliyun.oss.access-key}")
    private String accessKey;
    @Value("${aliyun.oss.secret-key}")
    private String secretKey;
    /** oss的储存名称 */
    @Value("${aliyun.oss.bucket}")
    private String bucket;
    //token
    @Value("${token}")
    private String token;
    //加密使用
    @Value("${key}")
    private String keyStr;
    //redis
    @Value("${redis.host}")
    private String host;

}
