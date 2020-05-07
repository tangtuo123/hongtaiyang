package com.hongtaiyang.admin.controller;

import com.hongtaiyang.admin.service.ResourceService;
import com.hongtaiyang.common.entity.HttpResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/3/16 10:36
 * @description：角色管理类
 * @version: 1.0.0
 */
@RestController
@RequestMapping(value = "resource")
@Api(tags = "资源菜单管理类")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping(value = "/list")
    @ApiOperation(value = "获取所有的资源菜单")
    public HttpResponse selectAll() {
        return HttpResponse.success(resourceService.selectAll(null));
    }


    @GetMapping(value = "/list/{roleId}")
    @ApiOperation(value = "跟据角色id获取当前角色所有的资源菜单")
    public HttpResponse selectByRoleId(@PathVariable Integer roleId) {
        return HttpResponse.success(resourceService.selectAll(roleId));
    }
}
