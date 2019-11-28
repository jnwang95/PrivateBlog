package com.wjn.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.wjn.bean.validator.AlterPassword;
import com.wjn.constant.NaturalNumber;
import com.wjn.exception.ServiceException;
import com.wjn.mapper.UserMapper;
import com.wjn.model.admin.User;
import com.wjn.service.AdminIndexService;
import com.wjn.utils.JsonReturnCode;
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
    private UserMapper userMapper;
    @Autowired
    private PasswordUtil passwordUtil;

    @Override
    public void alterPassword(AlterPassword alterPassword) {
        if(alterPassword.getPassword().length() <= NaturalNumber.six || alterPassword.getPassword().length() > 20){
            throw new ServiceException(JsonReturnCode.OLD_PASSWORD_FAIL);
        }
        if(alterPassword.getNewPassword().length() <= NaturalNumber.six || alterPassword.getNewPassword().length() > 20){
            throw new ServiceException(JsonReturnCode.NEW_PASSWORD_FAIL);
        }
        if(!alterPassword.getNewPassword().equals(alterPassword.getRenewPassword())){
            throw new ServiceException(JsonReturnCode.TWO_PASSWORD_FAIL);
        }
        String encryptPassword = passwordUtil.encrypt(alterPassword.getPassword());
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo(User.Fields.password,encryptPassword);
        List<User> users = userMapper.selectByExample(example);
        if(CollUtil.isEmpty(users)){
            throw new ServiceException(JsonReturnCode.TWO_PASSWORD_ERROR);
        }else {
            String newEncryptPassword = passwordUtil.encrypt(alterPassword.getNewPassword());
            users.get(NaturalNumber.zero).setPassword(newEncryptPassword);
            int i = userMapper.updateByPrimaryKeySelective(users.get(NaturalNumber.zero));
            if(i!= NaturalNumber.one){
                throw new ServiceException(JsonReturnCode.PASSWORD_CHANGE_FAIL);
            }
        }
    }

}
