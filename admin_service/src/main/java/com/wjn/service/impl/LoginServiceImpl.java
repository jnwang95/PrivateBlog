package com.wjn.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.wjn.bean.validator.LoginUser;
import com.wjn.exception.ServiceException;
import com.wjn.mapper.UserMapper;
import com.wjn.model.admin.User;
import com.wjn.service.LoginService;
import com.wjn.utils.JsonReturnCode;
import com.wjn.utils.PasswordUtil;
import com.wjn.utils.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import tk.mybatis.mapper.entity.Example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @auther WJN
 * @date 2019/10/12 0012 上午 12:17
 * @describe
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private PasswordUtil passwordUtil;

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
        example.createCriteria().andEqualTo(User.Fields.username,username);
        List<User> users = userMapper.selectByExample(example);
        if(CollUtil.isEmpty(users)){
            return false;
        }
        return password.equals(CollUtil.getFirst(users).getPassword());
    }

    @Override
    public String login(LoginUser loginUser, BindingResult result) {
        //验证账号密码的合法性
        if(result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (ObjectError error : result.getAllErrors()) {
                sb.append(error.getDefaultMessage()).append("\r\n");
            }
            throw new ServiceException(sb.toString());
        }
        //验证验证码
        if(StrUtil.isEmpty(loginUser.getUuid())){
            throw new ServiceException(JsonReturnCode.PARAME_ERROR);
        }

        Object pop = redisTemplate.opsForSet().pop(loginUser.getUuid());
        if(pop == null){
            throw new ServiceException(JsonReturnCode.VERIFICATION_CODE_EXPIRED);
        }
        if(!String.valueOf(pop).equals(loginUser.getVerify())){
            throw new ServiceException(JsonReturnCode.VERIFICATION_CODE_ERROR);
        }

        //获取加密的密码
        String encryptPassword = passwordUtil.encrypt(loginUser.getPassword());
        //登录
        ShiroUtil.login(loginUser.getUsername(),encryptPassword);
        //4.获取sessionId
        return ShiroUtil.getSessionId();
    }

    @Override
    public Map<String, String> captcha() throws IOException {
        //生成验证码图片
        CircleCaptcha lineCaptcha = CaptchaUtil.createCircleCaptcha(200, 100);
        String code = lineCaptcha.getCode();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BufferedImage image = lineCaptcha.getImage();
        ImageIO.write(image,"png",out);
        String imageBase64 = Base64.encode(out.toByteArray());
        String src = "data:image/png;base64,"+imageBase64;
        String uuid = UUID.fastUUID().toString();
        Map<String,String> map = new HashMap<>();
        map.put("src",src);
        map.put("uuid",uuid);
        redisTemplate.opsForSet().add(uuid, code);
        redisTemplate.expire(uuid,60, TimeUnit.SECONDS);
        return map;
    }
}
