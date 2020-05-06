package com.hongtaiyang.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hongtaiyang.common.entity.User;
import com.hongtaiyang.common.entity.dto.UserAdminDTO;

/**
 * @author tangtuo
 */
public interface IUserService extends IService<User> {

    /**
     * 跟据用户名查询
     *
     * @param userName
     * @return
     */
    User selectByUserName(String userName);

    /**
     * 创建用户
     *
     * @param user
     * @return
     */
    Integer createUser(User user);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    Integer updateUser(User user);

    /**
     * 跟据id删除用户,支持批量操作
     *
     * @param id
     * @return
     */
    Integer delete(String id);

    /**
     * 查询用户列表
     *
     * @param page
     * @param nickName
     * @return
     */
    IPage<UserAdminDTO> getUserList(Page<UserAdminDTO> page, String nickName);
}
