package com.wjn.model.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
@Data
public class TitleImg implements Serializable {

    //主键ID
    @ApiModelProperty("主键id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    private Integer id;
    //地址
    @ApiModelProperty("地址")
    private String url;
    //状态
    @ApiModelProperty("状态")
    private Integer state;
    //录入时间
    @ApiModelProperty("录入时间")
    private Date entryTime;

}
