package com.wjn.service;


import com.wjn.bean.dto.RegisterUserDto;

/**
 * @auther WJN
 * @date 2019/9/1 0001 下午 11:26
 * @describe
 */
public interface UserService {

   String getName(Integer id);

    /**
     * 保存注册用户
     * @param register
     */
    void register(RegisterUserDto register);
}
