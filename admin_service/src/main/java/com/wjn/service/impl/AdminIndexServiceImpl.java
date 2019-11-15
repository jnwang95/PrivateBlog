package com.wjn.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.wjn.bean.validator.AlterPassword;
import com.wjn.exception.ServiceException;
import com.wjn.mapper.UserMapper;
import com.wjn.model.admin.BeanConstant;
import com.wjn.model.admin.User;
import com.wjn.service.AdminIndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;


/**
 * @auther WJN
 * @date 2019/10/12 0012 下午 9:12
 * @describe
 */
@Service
@Slf4j
public class AdminIndexServiceImpl implements AdminIndexService {

    @Value("${key}")
    private String keyStr;
    @Resource
    private UserMapper userMapper;

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
        if(alterPassword.getPassword().length() <= 6 || alterPassword.getPassword().length() > 20){
            throw new ServiceException(501,"原密码不合法");
        }
        if(alterPassword.getNewPassword().length() <= 6 || alterPassword.getNewPassword().length() > 20){
            throw new ServiceException(502,"新密码不合法");
        }
        if(!alterPassword.getNewPassword().equals(alterPassword.getRenewPassword())){
            throw new ServiceException(503,"确认密码不相同");
        }

        byte[] key = keyStr.getBytes();
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        //加密为16进制表示 判断
        String encryptHex = aes.encryptHex(alterPassword.getPassword());
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo(BeanConstant.PASSWORD,encryptHex);
        List<User> users = userMapper.selectByExample(example);
        if(CollUtil.isEmpty(users)){
            throw new ServiceException(504,"原密码不正确");
        }else {
            String newEncryptHex = aes.encryptHex(alterPassword.getNewPassword());
            users.get(0).setPassword(newEncryptHex);
            int i = userMapper.updateByPrimaryKeySelective(users.get(0));
            if(i!=1){
                throw new ServiceException(505,"密码更新失败");
            }
        }
    }

}
