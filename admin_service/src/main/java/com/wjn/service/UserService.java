package com.wjn.service;


import com.wjn.bean.dto.RegisterUserDto;
import com.wjn.model.admin.User;

import java.util.List;

/**
 * @auther WJN
 * @date 2019/9/1 0001 下午 11:26
 * @describe
 */
public interface UserService {

   String getName(Integer id);

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

    List<Integer> getPermissionIdsByUsername(String username);
}
