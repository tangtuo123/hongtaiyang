package com.hongtaiyang.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hongtaiyang.common.entity.User;
import com.hongtaiyang.common.entity.dto.UserAdminDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

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
    IPage<UserAdminDTO> getUserList(Page<UserAdminDTO> page, @Param("nickName") String nickName);
}
