package com.wjn.model.admin;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @auther WJN
 * @date 2019/9/30 16:15
 * @describetion Banner
 */
@Data
public class Banner implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Long id;
    //名称标志
    private String html;
    //图片路径
    private String url;
    //内容
    private String content;

}
