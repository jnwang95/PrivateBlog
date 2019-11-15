package com.wjn.service;


import com.wjn.bean.validator.AlterPassword;

/**
 * @auther WJN
 * @date 2019/10/12 0012 下午 9:12
 * @describe
 */
public interface AdminIndexService {
    /**
     * 获取用户名
     * @return
     */
    String getUsername();

    /**
     * 修改密码
     */
    void alterPassword(AlterPassword alterPassword);
}
