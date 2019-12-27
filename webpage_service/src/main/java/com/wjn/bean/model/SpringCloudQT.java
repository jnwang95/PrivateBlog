package com.wjn.bean.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;

/**
 * @description:
 * @author: jnWang
 * @create: 2019-12-27 17:02
 */
@Getter
@Setter
@FieldNameConstants
public class SpringCloudQT implements Serializable {

    /**
     * id
     */
    private Long id;
    /**
     * 名称
     */
    private String title;
}
