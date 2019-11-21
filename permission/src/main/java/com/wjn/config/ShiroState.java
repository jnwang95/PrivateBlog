package com.wjn.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: Shiro状态常量
 * @author: jnWang
 * @create: 2019-11-21 13:58
 */
@Getter
@AllArgsConstructor
public enum ShiroState {

    NO_LOGIN(402,"未登录"),
    NO_PERMISSION(403,"未授权");

    private Integer code;
    private String desc;
}
