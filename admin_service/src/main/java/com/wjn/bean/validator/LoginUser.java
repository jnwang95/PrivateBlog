package com.wjn.bean.validator;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @auther WJN
 * @date 2019/10/11 17:06
 * @describetion 验证用户用
 */
@Data
public class LoginUser {
    @NotBlank(message="用户名不能为空")
    private String username;

    @NotBlank(message="密码不能为空")
    private String password;

    @NotBlank(message="验证码不能为空")
    private String verify;
}
