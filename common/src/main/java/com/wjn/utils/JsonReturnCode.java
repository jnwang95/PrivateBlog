package com.wjn.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 描述: json格式数据返回码
 *<ul>
 *      <li>100 : 用户未登录 </li>
 *      <li>200 : 成功 </li>
 *      <li>300 : 失败 </li>
 * </ul>
 * @author : WJN
 */
@Getter
@AllArgsConstructor
public enum JsonReturnCode {
    SUCCESS (200,"操作成功"),
    PARAME_ERROR (300,"参数错误"),
    FAIL (500,"公共错误"),

    VERIFICATION_CODE_EXPIRED(301,"验证码过期"),
    VERIFICATION_CODE_ERROR (302,"验证码错误"),

    OLD_PASSWORD_FAIL(501,"原密码不合法"),
    NEW_PASSWORD_FAIL(502,"新密码不合法"),
    TWO_PASSWORD_FAIL(503,"确认密码不相同"),
    TWO_PASSWORD_ERROR(504,"原密码不正确"),
    PASSWORD_CHANGE_FAIL(505,"密码更改失败"),
    NOT_LOGIN(401,"没有登录"),
	ACCESS_ERROR (403,"access_error"),
	NOT_FOUND (404,"资源未找到");

    private Integer code;
    private String desc;
}
