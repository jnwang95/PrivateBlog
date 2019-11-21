package com.wjn.model.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.shiro.authc.AuthenticationToken;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @auther WJN
 * @date 2019/9/1 0001 下午 11:21
 * @describe
 */
@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class User implements Serializable {
    /**
     * 主键ID,
     */
    @ApiModelProperty("主键id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    private Long id;

    /**
     * 名字
     */
    @ApiModelProperty("姓名")
    private String name;
    /**
     * 账号
     */
    @ApiModelProperty("账号")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;
    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;
    /**
     * 手机
     */
    @ApiModelProperty("手机号")
    private String tel;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态",example = "0？为注销或者封停，1？正常使用")
    private Integer state;
    /**
     * 注册日期
     */
    @ApiModelProperty("注册日期")
    private Date entryTime;
}
