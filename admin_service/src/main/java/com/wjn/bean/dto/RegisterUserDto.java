package com.wjn.bean.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * @auther WJN
 * @date 2019/9/8 0008 上午 1:09
 * @describe 注册用户DTO
 */
@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class RegisterUserDto {

    //名字
    @NotBlank(message="用户名不能为空")
    private String name;
    //账号
    @NotBlank(message="账号不能为空")
    @Length(min=6,max = 18,message="账号长度必须位于6到18之间")
    private String username;
    //密码
    @NotBlank(message="密码不能为空")
    @Length(min=6,message="密码长度不能小于6位")
    private String password;
    //邮箱
    @Email(message="请输入正确的邮箱")
    private String email;
    //手机
    @NotBlank(message="手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$",message = "手机号格式有误")
    private String tel;
}
