package com.hongtaiyang.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hongtaiyang.admin.service.ResourceService;
import com.hongtaiyang.admin.service.RoleService;
import com.hongtaiyang.common.entity.HttpResponse;
import com.hongtaiyang.common.entity.Resource;
import com.hongtaiyang.common.entity.Role;
import com.hongtaiyang.common.entity.dto.RoleDTO;
import com.hongtaiyang.common.entity.request.RoleRequest;
import com.hongtaiyang.common.validate.UpdateGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/3/16 10:36
 */
@RestController
@RequestMapping(value = "/role")
@Api(tags = "角色管理类")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @PostMapping(value = "/create")
    @ApiOperation(value = "创建角色")
    public HttpResponse createRole(@Validated @RequestBody RoleRequest request) {
        roleService.createRole(request);
        return HttpResponse.success(true);
    }

    @ApiOperation(value = "查询列表")
    @GetMapping(value = "/list/{pageNum}/{pageSize}")
    public HttpResponse getRoleList(@PathVariable Integer pageNum, @PathVariable Integer pageSize, @RequestParam(required = false) String name) {
        Page<RoleDTO> page = new Page(pageNum, pageSize);
        IPage<RoleDTO> pageList = roleService.selectAll(page, name);
        return HttpResponse.success(pageList);
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping(value = "/delete/{id}")
    public HttpResponse delById(@PathVariable String id) {
        List<String> list = Arrays.asList(id.split(","));
        roleService.delById(list);
        return HttpResponse.success(true);
    }

    @ApiOperation(value = "获取角色详情")
    @GetMapping(value = "/detail/{id}")
    public HttpResponse getById(@PathVariable Integer id) {
        Role role = roleService.getById(id);
        List<Resource> list = resourceService.selectAll(id);
        Map<String, Object> result = new HashMap<>();
        result.put("role", role);
        result.put("resourceMenu", list);
        return HttpResponse.success(result);
    }

    @ApiOperation(value = "修改角色")
    @PutMapping(value = "/update")
    public HttpResponse updateById(@Validated(value = UpdateGroup.class) @RequestBody RoleRequest request) {
        roleService.updateById(request);
        return HttpResponse.success(true);
    }

    @ApiOperation(value = "获取所有角色列表,供创建用户时使用,不做分页处理")
    @GetMapping(value = "/list/all")
    public HttpResponse getAll() {
        return HttpResponse.success(roleService.getAll());
    }

}
