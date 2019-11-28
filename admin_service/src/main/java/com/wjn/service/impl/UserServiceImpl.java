package com.wjn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.wjn.bean.dto.RegisterUserDto;
import com.wjn.constant.NaturalNumber;
import com.wjn.mapper.RoleMapper;
import com.wjn.mapper.RolePermissionMapper;
import com.wjn.mapper.UserMapper;
import com.wjn.mapper.UserRoleMapper;
import com.wjn.model.admin.Role;
import com.wjn.model.admin.RolePermission;
import com.wjn.model.admin.User;
import com.wjn.model.admin.UserRole;
import com.wjn.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @auther WJN
 * @date 2019/9/1 0001 下午 11:26
 * @describe
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void register(RegisterUserDto register) {
        User user = new User();
        BeanUtil.copyProperties(register,user);
        String password = BCrypt.hashpw(user.getPassword());
        user.setPassword(password);
        user.setState(NaturalNumber.one);
        user.setEntryTime(DateUtil.date());
        userMapper.insert(user);
    }

    @Override
    public User findByName(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo(User.Fields.username,username);
        List<User> users = userMapper.selectByExampleAndRowBounds(example, new RowBounds(NaturalNumber.zero, NaturalNumber.one));
        return CollUtil.getFirst(users);
    }

    @Override
    public List<Integer> getPermissionIdsByRoleId(Integer roleId) {
        Example example = new Example(RolePermission.class);
        example.selectProperties(RolePermission.Fields.permissionId);
        example.createCriteria().andEqualTo(RolePermission.Fields.roleId,roleId);
        List<RolePermission> rolePermissions = rolePermissionMapper.selectByExample(example);
        return rolePermissions.stream().map(RolePermission::getPermissionId).collect(Collectors.toList());
    }

    @Override
    public List<Integer> getRoleIdsByUserId(Long userId) {
        Example example = new Example(UserRole.class);
        example.createCriteria().andEqualTo(UserRole.Fields.userId,userId);
        List<UserRole> userRoles = userRoleMapper.selectByExample(example);
        return userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
    }

    @Override
    public Set<String> getRoleNameByRoleIds(List<Integer> roleIds) {
        Example example = new Example(Role.class);
        example.selectProperties(Role.Fields.roleName).setDistinct(true);
        example.createCriteria().andIn(Role.Fields.id,roleIds);
        List<Role> roles = roleMapper.selectByExample(example);
        return roles.stream().map(Role::getRoleName).collect(Collectors.toSet());
    }
}
