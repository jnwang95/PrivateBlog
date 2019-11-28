package com.wjn.model.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @auther WJN
 * @date 2019/10/20 0020 下午 2:49
 * @describe
 */
@Getter
@Setter
@FieldNameConstants
public class TitleImg implements Serializable {

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 地址
     */
    private String url;
    /**
     * 状态
     */
    private Integer state;
    /**
     * 录入时间
     */
    private Date entryTime;

}
