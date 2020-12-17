package com.hongtaiyang.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hongtaiyang.common.entity.Resource;

import java.util.List;

public interface ResourceService extends IService<Resource> {

    List<Resource> selectAll(Integer roleId) throws InterruptedException;

    List<Resource> selectFromCache();

    List<Resource> selectByRoleId(Integer roleId);

}
