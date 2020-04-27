package com.hongtaiyang.admin.aop;

import com.hongtaiyang.admin.service.ISysLogService;
import com.hongtaiyang.common.entity.SysLog;
import com.hongtaiyang.common.utils.IpUtil;
import com.hongtaiyang.common.utils.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/4/25 13:30
 */
@Slf4j
@Aspect
@Component
public class SysLogAspect {


    @Autowired
    private ISysLogService sysLogService;

    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    public void sysLog() {

    }

    @Before(value = "sysLog()")
    public void before(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (!request.getServletPath().contains("user/login")) {
            SysLog sysLog = new SysLog();
            String ip = IpUtil.getIp(request);
            sysLog.setIp(ip);
            Integer userId = JWTUtil.getUserId(request.getHeader("Authorization"));
            sysLog.setUserId(userId);
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            ApiOperation annotation = method.getAnnotation(ApiOperation.class);
            sysLog.setMethod(annotation.value());
            Class<?> clazz = joinPoint.getTarget().getClass();
            Api api = clazz.getAnnotation(Api.class);
            sysLog.setModule(StringUtils.join(api.tags(), ","));
            sysLogService.save(sysLog);
        }
    }
}
