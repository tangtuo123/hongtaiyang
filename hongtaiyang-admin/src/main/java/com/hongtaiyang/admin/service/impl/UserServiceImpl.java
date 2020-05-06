package com.hongtaiyang.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongtaiyang.admin.mapper.UserMapper;
import com.hongtaiyang.admin.service.IUserService;
import com.hongtaiyang.common.entity.User;
import com.hongtaiyang.common.entity.dto.UserAdminDTO;
import com.hongtaiyang.common.utils.EncryptUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/4/24 17:11
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
        queryWrapper.eq("del_flag",0);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer createUser(User user) {
        // 昵称为空的话,默认位用户名
        if (StringUtils.isBlank(user.getNickName())) {
            user.setNickName(user.getUserName());
        }
        user.setPassword(EncryptUtil.encrypt(user.getPassword()));
        return userMapper.insert(user);
    }

    @Override
    public Integer updateUser(User user) {
        if (StringUtils.isNotBlank(user.getPassword())) {
            user.setPassword(EncryptUtil.encrypt(user.getPassword()));
        }
        return userMapper.updateUser(user);
    }

    @Override
    public Integer delete(String id) {
        return userMapper.delete(id);
    }

    @Override
    public IPage<UserAdminDTO> getUserList(Page<UserAdminDTO> page, String nickName) {
        return userMapper.getUserList(page, nickName);
    }
}
