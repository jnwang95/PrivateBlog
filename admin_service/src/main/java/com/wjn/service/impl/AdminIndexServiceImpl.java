package com.wjn.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.wjn.bean.validator.AlterPassword;
import com.wjn.constant.NaturalNumber;
import com.wjn.constant.UserEnum;
import com.wjn.exception.ServiceException;
import com.wjn.mapper.UserMapper;
import com.wjn.model.admin.User;
import com.wjn.service.AdminIndexService;
import com.wjn.config.ConfigConstant;
import com.wjn.utils.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


/**
 * @auther WJN
 * @date 2019/10/12 0012 下午 9:12
 * @describe
 */
@Service
@Slf4j
public class AdminIndexServiceImpl implements AdminIndexService {


    @Autowired
    private ConfigConstant configConstant;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordUtil passwordUtil;

    @Override
    public String getUsername() {
        List<User> users = userMapper.selectAll();
        StringBuilder sb = new StringBuilder();
        for (User user : users) {
            sb.append(user.getName());
        }
        return sb.toString();
    }

    @Override
    public void alterPassword(AlterPassword alterPassword) {
        if(alterPassword.getPassword().length() <= NaturalNumber.six || alterPassword.getPassword().length() > 20){
            throw new ServiceException(501,"原密码不合法");
        }
        if(alterPassword.getNewPassword().length() <= NaturalNumber.six || alterPassword.getNewPassword().length() > 20){
            throw new ServiceException(502,"新密码不合法");
        }
        if(!alterPassword.getNewPassword().equals(alterPassword.getRenewPassword())){
            throw new ServiceException(503,"确认密码不相同");
        }
        String encryptPassword = passwordUtil.encrypt(alterPassword.getPassword());
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo(UserEnum.password.name(),encryptPassword);
        List<User> users = userMapper.selectByExample(example);
        if(CollUtil.isEmpty(users)){
            throw new ServiceException(504,"原密码不正确");
        }else {
            String newEncryptPassword = passwordUtil.encrypt(alterPassword.getNewPassword());
            users.get(NaturalNumber.zero).setPassword(newEncryptPassword);
            int i = userMapper.updateByPrimaryKeySelective(users.get(NaturalNumber.zero));
            if(i!= NaturalNumber.one){
                throw new ServiceException(505,"密码更新失败");
            }
        }
    }

    public void main(String[] args) throws Throwable {

    }

}
