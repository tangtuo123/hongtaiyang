package com.hongtaiyang.admin.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hongtaiyang.common.entity.Role;
import com.hongtaiyang.common.entity.dto.RoleDTO;
import com.hongtaiyang.common.entity.request.RoleRequest;

import java.util.List;


public interface RoleService extends IService<Role> {

    Integer createRole(RoleRequest request);

    IPage<RoleDTO> selectAll(Page<RoleDTO> page, String name);

    Integer delById(List list);

    Role getById(Integer id);

    Integer updateById(RoleRequest request);

    List<Role> getAll();



}
