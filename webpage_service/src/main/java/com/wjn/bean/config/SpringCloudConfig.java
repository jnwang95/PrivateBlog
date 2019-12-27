package com.wjn.bean.config;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

/**
 * @description: Springcloud文章配置器
 * @author: jnWang
 * @create: 2019-12-26 17:48
 */
@FieldNameConstants
@Setter
@Getter
public class SpringCloudConfig {

    public static String SPRINGCLOUD_GITEE = "springcloud_gitee";

    public static String SPRINGCLOUD_GITHUB = "springcloud_github";
}
