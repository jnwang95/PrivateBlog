package com.wjn.utils;

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
public enum JsonReturnCode {

    NOT_LOGIN("401","not_login"),
    SUCCESS ("200","success"),
    FAIL ("500","fail"),
	ACCESS_ERROR ("403","access_error"),
	NOT_FOUND ("404","not_found");
    private String code;
    private String desc;

    JsonReturnCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
//        return Resources.getMessage(this.desc);
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
