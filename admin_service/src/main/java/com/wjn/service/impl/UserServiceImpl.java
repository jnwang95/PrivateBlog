package com.wjn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.wjn.bean.dto.RegisterUserDto;
import com.wjn.constant.NaturalNumber;
import com.wjn.constant.RolePermissionEnum;
import com.wjn.constant.UserEnum;
import com.wjn.constant.UserRoleEnum;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        User user = User.of();
        BeanUtil.copyProperties(register,user);
        String password = BCrypt.hashpw(user.getPassword());
                 user
                .setPassword(password)
                .setState(NaturalNumber.one)
                .setEntryTime(DateUtil.date());
        userMapper.insert(user);
    }

    @Override
    public User findByName(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo(UserEnum.username.name(),username);
        List<User> users = userMapper.selectByExampleAndRowBounds(example, new RowBounds(NaturalNumber.zero, NaturalNumber.one));
        return users.get(NaturalNumber.zero);
    }

    @Override
    public List<Integer> getPermissionIdsByRoleId(Integer roleId) {
        Example example = new Example(RolePermission.class);
        example.selectProperties(RolePermissionEnum.permissionId.name());
        example.createCriteria().andEqualTo(RolePermissionEnum.roleId.name(),roleId);
        List<RolePermission> rolePermissions = rolePermissionMapper.selectByExample(example);
        List<Integer> list = new ArrayList<>();
        for (RolePermission rolePermission : rolePermissions) {
            list.add(rolePermission.getPermissionId());
        }
        return list;
    }

    @Override
    public List<Integer> getRoleIdsByUserId(Long userId) {
        Example example = new Example(UserRole.class);
        example.createCriteria().andEqualTo(UserRoleEnum.userId.name(),userId);
        List<UserRole> userRoles = userRoleMapper.selectByExample(example);
        List<Integer> roleIds = new ArrayList<>();
        for (UserRole userRole : userRoles) {
            roleIds.add(userRole.getRoleId());
        }
        return roleIds;
    }

    @Override
    public Set<String> getRoleNameByRoleIds(List<Integer> roleIds) {
        Set<String> roleNames = new HashSet<>();
        for (Integer roleId : roleIds) {
            Role role = roleMapper.selectByPrimaryKey(roleId);
            roleNames.add(role.getRoleName());
        }
        return roleNames;
    }
}
