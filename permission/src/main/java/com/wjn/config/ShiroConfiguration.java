package com.wjn.config;

import com.wjn.session.MySessionManager;
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
    //1.创建realm
    @Bean
    public MyRealm getRealm() {
        return new MyRealm();
    }

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
         //1.创建过滤器工厂
         ShiroFilterFactoryBean filterFactory = new ShiroFilterFactoryBean();
         //2.设置安全管理器
         filterFactory.setSecurityManager(securityManager);

         //3.通用配置（跳转登录页面，未授权跳转的页面）
         filterFactory.setLoginUrl("/401?code="+ ShiroState.NO_LOGIN.getCode());//跳转url地址
         filterFactory.setUnauthorizedUrl("/401?code="+ ShiroState.NO_PERMISSION.getCode());//未授权的url
         //4.设置过滤器集合
         Map<String,String> filterMap = new LinkedHashMap<>();
         //配置请求连接过滤器配置
         //匿名访问（所有人员可以使用），这里设置登录和图形验证码所有人可以访问
         /*
          * /doc.html    SwaggerBootstrapUi提供的文档访问地址
          * /api-docs-ext    SwaggerBootstrapUi提供的增强接口地址
          * /swagger-resources    Springfox-Swagger提供的分组接口
          * /api-docs    Springfox-Swagger提供的分组实例详情接口
          * /swagger-ui.html    Springfox-Swagger提供的文档访问地址
          * /swagger-resources/configuration/ui    Springfox-Swagger提供
          * /swagger-resources/configuration/security    Springfox-Swagger提供
          */

         filterMap.put("/druid/**", "anon");
         filterMap.put("/login", "anon");
         filterMap.put("/captcha", "anon");
         filterMap.put("/logout", "anon");
         filterMap.put("/401", "anon");
         //自定义认证方式
         filterMap.put("/**", "authc");
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
        MySessionManager sessionManager = new MySessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        //禁用cookie
        sessionManager.setSessionIdCookieEnabled(false);
        //禁用重写url
        sessionManager.setSessionIdUrlRewritingEnabled(false);
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
