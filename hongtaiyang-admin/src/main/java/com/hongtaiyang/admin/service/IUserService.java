package com.hongtaiyang.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hongtaiyang.common.entity.User;

/**
 * @author tangtuo
 */
public interface IUserService extends IService<User> {

    User selectByUserName(String userName);
}
