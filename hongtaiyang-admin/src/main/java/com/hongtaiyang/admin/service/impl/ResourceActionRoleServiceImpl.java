package com.hongtaiyang.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongtaiyang.admin.mapper.ResourceActionRoleMapper;
import com.hongtaiyang.admin.service.ResourceActionRoleService;
import com.hongtaiyang.common.entity.ResourceActionRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author ：tangtuo
 * @date ：Created in 2020/3/16 10:00
 * @version: 1.0.0
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class ResourceActionRoleServiceImpl extends ServiceImpl<ResourceActionRoleMapper, ResourceActionRole> implements ResourceActionRoleService {

    @Autowired
    private ResourceActionRoleMapper resourceActionRoleMapper;

    @Override
    public Integer deleteByRoleId(List list) {
        return resourceActionRoleMapper.deleteByRoleId(list);
    }
}
