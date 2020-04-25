package com.hongtaiyang.admin.controller;

import com.hongtaiyang.admin.service.ISysLogService;
import com.hongtaiyang.admin.service.IUserService;
import com.hongtaiyang.common.constant.RedisConstant;
import com.hongtaiyang.common.constant.TerminalTypeConstant;
import com.hongtaiyang.common.entity.HttpResponse;
import com.hongtaiyang.common.entity.SysLog;
import com.hongtaiyang.common.entity.User;
import com.hongtaiyang.common.enums.SystemCode;
import com.hongtaiyang.common.exception.SysException;
import com.hongtaiyang.common.utils.JWTUtil;
import com.hongtaiyang.common.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author ：zhangyu
 */
@RestController
@RequestMapping("/syslog")
@Api(tags = "系统日志管理类")
public class SysLogController {

    @Autowired
    private ISysLogService sysLogService;

    @PostMapping("/getLogs")
    @ApiOperation(value = "获取所有日志链表")
    public HttpResponse login(Long startTime, Long endTime) {
        List<SysLog> sysLogList = null;
        if (startTime == null && endTime == null) {
            sysLogList = sysLogService.list();
        }
       // todo：起始时间
        return HttpResponse.success(sysLogList);
    }
}
