package com.hongtaiyang.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongtaiyang.admin.mapper.ResourceMapper;
import com.hongtaiyang.admin.service.ResourceService;
import com.hongtaiyang.common.entity.Resource;
import com.hongtaiyang.common.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * @author ：tangtuo
 * @date ：Created in 2020/3/16 10:00
 * @version: 1.0.0
 */
@Slf4j
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedissonClient redisson;

    @Override
    public List<Resource> selectAll(Integer roleId) throws InterruptedException {
        String redisKey = "resource:all";
        if (redisUtil.get(redisKey) != null) {
            log.info("开始查询缓存");
            String resourceListJson = (String) redisUtil.get(redisKey);
            return JSONObject.parseArray(resourceListJson, Resource.class);
        }
        return selectFromDB();
    }

    public List<Resource> selectFromDB() throws InterruptedException {
        List<Resource> result = new LinkedList<>();
        String redisKey = "resource:all";
        String uuid = UUID.randomUUID().toString();
        Boolean lock = redisUtil.addLock("lock", uuid, 10L);
        if (lock) {
            if (redisUtil.get(redisKey) != null) {
                log.info("开始查询缓存------");
                String resourceListJson = (String) redisUtil.get(redisKey);
                return JSONObject.parseArray(resourceListJson, Resource.class);
            }
            log.info("缓存没查到，开始查询数据库");
            List<Resource> list = resourceMapper.selectAll();
            list.forEach(resource -> {
                if (resource.getPid() == 0) {
                    result.add(resource);
                }
            });
            result.forEach(resource -> resource.setChildren(getChild(resource.getId(), list)));
            redisUtil.set(redisKey, JSONObject.toJSONString(result), 3600000);
            redisUtil.delLock("lock", uuid);
        } else {
            Thread.sleep(1000);
            selectFromDB();
        }
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

    @Cacheable(value = "resource")
    @Override
    public List<Resource> selectFromCache() {

        List<Resource> result = new LinkedList<>();
        System.out.println("缓存没查到,开始查询数据库");
        List<Resource> list = resourceMapper.selectAll();
        list.forEach(resource -> {
            if (resource.getPid() == 0) {
                result.add(resource);
            }
        });
        result.forEach(resource -> resource.setChildren(getChild(resource.getId(), list)));
        return result;
    }

    @Override
    @Cacheable(value = "resource", key = "#roleId")
    public List<Resource> selectByRoleId(Integer roleId) {
        List<Resource> resources = resourceMapper.selectByRoleId(roleId);
        List<Resource> result = new LinkedList<>();
        resources.forEach(resource -> {
            if (resource.getPid() == 0) {
                result.add(resource);
            }
        });
        result.forEach(resource -> resource.setChildren(getChild(resource.getId(), resources)));
        return result;
    }
}
