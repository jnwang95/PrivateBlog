package com.wjn.service;


import com.wjn.bean.dto.RegisterUserDto;
import com.wjn.model.admin.User;

import java.util.List;
import java.util.Set;

/**
 * @auther WJN
 * @date 2019/9/1 0001 下午 11:26
 * @describe
 */
public interface UserService {

    /**
     * 保存注册用户
     * @param register
     */
    void register(RegisterUserDto register);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findByName(String username);

     /**
      * 根据角色Id获取到权限Id集合
      * @param roleId
      * @return
      */
     List<Integer> getPermissionIdsByRoleId(Integer roleId);


    /**
     * 通过userid获取roleids
     * @param userId
     * @return
     */
    List<Integer> getRoleIdsByUserId(Long userId);

    /**
     * 通过角色id获取角色名
     * @param roleIds
     * @return
     */
    Set<String> getRoleNameByRoleIds(List<Integer> roleIds);
}
