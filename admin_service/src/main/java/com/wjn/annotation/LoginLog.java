package com.wjn.annotation;

import java.lang.annotation.*;

/**
 * 系统日志
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginLog {
}
