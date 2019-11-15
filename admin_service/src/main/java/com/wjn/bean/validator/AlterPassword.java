package com.wjn.bean.validator;

import lombok.Data;

/**
 * @auther WJN
 * @date 2019/10/12 0012 下午 11:52
 * @describe 修改密码bean
 */
@Data
public class AlterPassword {

    private String password;

    private String newPassword;

    private String renewPassword;

}
