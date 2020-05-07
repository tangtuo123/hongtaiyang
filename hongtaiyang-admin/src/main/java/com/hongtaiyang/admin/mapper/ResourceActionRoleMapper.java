package com.hongtaiyang.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongtaiyang.common.entity.ResourceActionRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ResourceActionRoleMapper extends BaseMapper<ResourceActionRole> {

    Integer deleteByRoleId(List list);
}
