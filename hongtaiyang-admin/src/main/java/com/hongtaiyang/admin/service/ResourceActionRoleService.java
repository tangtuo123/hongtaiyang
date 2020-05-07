package com.hongtaiyang.admin.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.hongtaiyang.common.entity.ResourceActionRole;

import java.util.List;

public interface ResourceActionRoleService extends IService<ResourceActionRole> {

    Integer deleteByRoleId(List list);

}
