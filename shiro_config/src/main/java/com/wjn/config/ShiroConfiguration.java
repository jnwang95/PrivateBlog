package com.wjn.config;

import com.wjn.session.MySessionManager;
import com.wjn.util.JWTFilter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @description: Shiro配置
 * @author: jnWang
 * @create: 2019-11-19 16:43
 */
@Configuration
public class ShiroConfiguration {
    @Autowired
    private ConfigConstant configConstant;

    /**
     * 2.创建安全管理器
     */
    @Bean
    public SecurityManager securityManager(MyRealm realm) {
     //使用默认的安全管理器
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(realm);
     //将自定义的realm交给安全管理器统一调度管理
     securityManager.setRealm(realm);
        //将自定义的会话管理器注册到安全管理器中
        securityManager.setSessionManager(sessionManager());
        //将自定义的redis缓存管理器注册到安全管理器中
        securityManager.setCacheManager(cacheManager());
     return securityManager;
    }


    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     * @param securityManager
     * @return
     */
     @Bean
     public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        //创建shiro过滤器工厂
        ShiroFilterFactoryBean filterFactory = new ShiroFilterFactoryBean();

         //添加自己的过滤器并且取名为jwt
         Map<String, Filter> filterMapJWT = new HashMap<>();
         filterMapJWT.put("jwt", new JWTFilter());
         filterFactory.setFilters(filterMapJWT);
         filterFactory.setSecurityManager(securityManager);
        //4.配置过滤器集合
        /*
         * key ：访问连接
         *     支持通配符的形式
         * value：过滤器类型
         *     shiro常用过滤器
         *         anno   ：匿名访问（表明此链接所有人可以访问）
         *         authc   ：认证后访问（表明此链接需登录认证成功之后可以访问）
         */
        Map<String,String> filterMap = new LinkedHashMap<String,String>();
        //配置请求连接过滤器配置
        //匿名访问（所有人员可以使用），这里设置登录和图形验证码所有人可以访问
        filterMap.put("/login", "anon");
        filterMap.put("/captcha", "anon");
        //自定义认证方式
        filterMap.put("/**", "jwt");
        //5.设置过滤器
        filterFactory.setFilterChainDefinitionMap(filterMap);
        return filterFactory;
    }

    /**
     * 1.redis的控制器，操作redis
     */
    private RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(configConstant.getHost());
        return redisManager;
    }

    /**
     * 2.sessionDao
     */
    private RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO sessionDAO = new RedisSessionDAO();
        sessionDAO.setRedisManager(redisManager());
        return sessionDAO;
    }

    /**
     * 3.会话管理器
     */
    private DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //MySessionManager sessionManager = new MySessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /**
     * 4.缓存管理器
     */
    private RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    //开启对shior注解的支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
