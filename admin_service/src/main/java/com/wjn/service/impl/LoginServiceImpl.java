package com.wjn.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.wjn.mapper.UserMapper;
import com.wjn.model.admin.User;
import com.wjn.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @auther WJN
 * @date 2019/10/12 0012 上午 12:17
 * @describe
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 通过传递过来的账号和加密的密码进行数据库调查，如果账号密码正确则返回true
     * 否则返回false
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean JudgeUser(String username, String password) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username",username);
        List<User> users = userMapper.selectByExample(example);
        if(CollUtil.isEmpty(users)){
            return false;
        }
        return password.equals(users.get(0).getPassword());
    }
}
