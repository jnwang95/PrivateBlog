package com.wjn.bean.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description:
 * @author: jnWang
 * @create: 2019-12-13 15:46
 */
@Getter
@Setter
public class SpringBootQT implements Serializable {
    /**
     * id
     */
    private Long id;
    /**
     * 名称
     */
    private String title;
}
