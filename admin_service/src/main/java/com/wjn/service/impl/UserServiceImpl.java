package com.wjn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.wjn.bean.dto.RegisterUserDto;
import com.wjn.mapper.UserMapper;
import com.wjn.model.admin.User;
import com.wjn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther WJN
 * @date 2019/9/1 0001 下午 11:26
 * @describe
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public String getName(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);

        return user.getName();
    }

    @Override
    public void register(RegisterUserDto register) {
        User user = User.of();
        
        BeanUtil.copyProperties(register,user);
        String password = BCrypt.hashpw(user.getPassword());
                 user
                .setPassword(password)
                .setState(1)
                .setEntryTime(DateUtil.date());
        userMapper.insert(user);
    }
}
