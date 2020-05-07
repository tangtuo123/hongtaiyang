package com.hongtaiyang.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongtaiyang.common.entity.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ResourceMapper extends BaseMapper<Resource> {

    List<Resource> selectAll();

    List<Resource> selectByRoleId(Integer roleId);
}
