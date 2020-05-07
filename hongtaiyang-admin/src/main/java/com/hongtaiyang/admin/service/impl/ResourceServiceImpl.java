package com.hongtaiyang.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongtaiyang.admin.mapper.ResourceMapper;
import com.hongtaiyang.admin.service.ResourceService;
import com.hongtaiyang.common.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * @author ：tangtuo
 * @date ：Created in 2020/3/16 10:00
 * @version: 1.0.0
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<Resource> selectAll(Integer roleId) {
        List<Resource> list;
        if (null == roleId) {
            list = resourceMapper.selectAll();
        } else {
            list = resourceMapper.selectByRoleId(roleId);
        }
        List<Resource> result = new LinkedList<>();
        list.forEach(resource -> {
            if (resource.getPid() == 0) {
                result.add(resource);
            }
        });
        result.forEach(resource -> resource.setChildren(getChild(resource.getId(), list)));
        return result;
    }


    public List<Resource> getChild(Integer pid, List<Resource> list) {
        List<Resource> childList = new ArrayList<>();
        list.forEach(resource -> {
            if (pid.equals(resource.getPid())) {
                childList.add(resource);
            }
        });
        // 递归
        childList.forEach(resource -> resource.setChildren(getChild(resource.getId(), childList)));
        return childList;
    }


}
