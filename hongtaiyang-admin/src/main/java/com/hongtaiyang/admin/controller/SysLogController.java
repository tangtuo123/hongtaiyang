package com.hongtaiyang.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hongtaiyang.admin.service.ISysLogService;

import com.hongtaiyang.common.entity.HttpResponse;
import com.hongtaiyang.common.entity.dto.SysLogDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：zhangyu
 */
@RestController
@RequestMapping("/sysLog")

public class SysLogController {

    @Autowired
    private ISysLogService sysLogService;

    @GetMapping("/list/{pageNum}/{pageSize}")
    @ApiOperation(value = "获取所有日志链表")
    public HttpResponse getLogList(@PathVariable Integer pageNum, @PathVariable Integer pageSize, @RequestParam(required = false) String startTime, @RequestParam(required = false) String endTime) {
        Page page = new Page<>(pageNum, pageSize);
        IPage<SysLogDTO> logList = sysLogService.getLogList(page, startTime, endTime);
        return HttpResponse.success(logList);
    }
}
