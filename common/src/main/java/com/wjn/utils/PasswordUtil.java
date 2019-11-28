package com.wjn.utils;

import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.wjn.config.ConfigConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 密码工具类
 * @author: jnWang
 * @create: 2019-11-20 13:42
 */
@Component
public class PasswordUtil{

    @Autowired
    private ConfigConstant configConstant;


    /**
     * 给密码加密
     * @param password
     * @return
     */
    public String encrypt(String password){
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, configConstant.getKeyStr().getBytes());
        return aes.encryptHex(password);
    }

    /**
     * 密码解密
     */
    public String decrypt(String encryptPassword){
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, configConstant.getKeyStr().getBytes());
        return aes.decryptStr(encryptPassword);
    }
}
