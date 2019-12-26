package com.wjn.bean.config;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

/**
 * @description: Springboot文章配置器
 * @author: jnWang
 * @create: 2019-12-26 17:48
 */
@FieldNameConstants
@Setter
@Getter
public class SpringbootConfig {

    public static String SPRINGBOOT_GITEE = "springboot_gitee";

    public static String SPRINGBOOT_GITHUB = "springboot_github";
}
