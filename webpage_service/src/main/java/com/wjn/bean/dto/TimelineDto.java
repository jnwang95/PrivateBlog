package com.wjn.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;

/**
 * @auther WJN
 * @date 2019/12/23 0023 下午 10:20
 * @describe 时间轴bean
 */
@Getter
@Setter
@FieldNameConstants
public class TimelineDto implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * 辩题
     */
    private String title;
    /**
     * 标题时间
     */
    private String titleTime;
    /**
     * 具体时间
     */
    private String subTime;
    /**
     * 作者
     */
    private String name = "白纸画卷";
}
