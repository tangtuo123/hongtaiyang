package com.hongtaiyang.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongtaiyang.admin.mapper.UserMapper;
import com.hongtaiyang.admin.service.IUserService;
import com.hongtaiyang.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/4/24 17:11
 * @description：
 * @version: 1.0.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByUserName(String userName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        return userMapper.selectOne(queryWrapper);
    }
}
