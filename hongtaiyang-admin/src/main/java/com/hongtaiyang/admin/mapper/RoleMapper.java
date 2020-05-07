package com.hongtaiyang.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hongtaiyang.common.entity.Role;
import com.hongtaiyang.common.entity.dto.RoleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    IPage<RoleDTO> selectAll(Page<RoleDTO> page, @Param("name") String name);

    Integer delById(@Param("list") List list);

    Integer modifyById(Role role);

    List<Role> getAll();


}
