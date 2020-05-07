package com.hongtaiyang.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongtaiyang.admin.mapper.RoleMapper;
import com.hongtaiyang.admin.service.ResourceActionRoleService;
import com.hongtaiyang.admin.service.RoleService;
import com.hongtaiyang.common.entity.ResourceActionRole;
import com.hongtaiyang.common.entity.Role;
import com.hongtaiyang.common.entity.dto.RoleDTO;
import com.hongtaiyang.common.entity.request.ResourceRequest;
import com.hongtaiyang.common.entity.request.RoleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;


/**
 * @author ：tangtuo
 * @date ：Created in 2020/3/16 10:00
 * @version: 1.0.0
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ResourceActionRoleService resourceActionRoleService;

    @Override
    public Integer createRole(RoleRequest request) {
        Role role = request.getRole();
        int i = roleMapper.insert(role);
        List<ResourceRequest> resourceMenu = request.getResourceMenu();
        List<ResourceActionRole> list = new LinkedList<>();
        resourceMenu.forEach(resourceRequest -> {
            ResourceActionRole resourceActionRole = new ResourceActionRole();
            resourceActionRole.setActionId(resourceRequest.getActionId());
            resourceActionRole.setRoleId(role.getId());
            resourceActionRole.setResourceId(resourceRequest.getResourceId());
            list.add(resourceActionRole);
        });
        resourceActionRoleService.saveBatch(list);
        return i;
    }

    @Override
    public IPage<RoleDTO> selectAll(Page<RoleDTO> page, String name) {
        return roleMapper.selectAll(page, name);
    }

    @Override
    public Integer delById(List list) {
        resourceActionRoleService.deleteByRoleId(list);
        return roleMapper.delById(list);
    }

    @Override
    public Role getById(Integer id) {
        return roleMapper.selectById(id);
    }

    @Override
    public Integer updateById(RoleRequest request) {
        List<Integer> list = new LinkedList<>();
        Role role = request.getRole();
        list.add(role.getId());
        resourceActionRoleService.deleteByRoleId(list);
        List<ResourceActionRole> resourceActionRoles = new LinkedList<>();
        List<ResourceRequest> resourceMenu = request.getResourceMenu();
        resourceMenu.forEach(resourceRequest -> {
            ResourceActionRole resourceActionRole = new ResourceActionRole();
            resourceActionRole.setRoleId(role.getId());
            resourceActionRole.setActionId(resourceRequest.getActionId());
            resourceActionRole.setResourceId(resourceRequest.getResourceId());
            resourceActionRoles.add(resourceActionRole);
        });
        resourceActionRoleService.saveBatch(resourceActionRoles);
        return roleMapper.modifyById(role);
    }

    @Override
    public List<Role> getAll() {
        return roleMapper.getAll();
    }


}
