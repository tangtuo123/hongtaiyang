package com.hongtaiyang.common.interceptor;

import com.hongtaiyang.common.annotation.Authentication;
import com.hongtaiyang.common.utils.JWTUtil;
import com.hongtaiyang.common.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author ：tangtuo
 * @date ：Created in 2019/8/13 16:48
 * @description：认证拦截器
 * @version: 1.0.0
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {
        // 从 http 请求头中取出 token
        String token = request.getHeader("Authorization");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有Authentication注释，有则认证
        if (method.isAnnotationPresent(Authentication.class)) {
            // 判断token是否为空
            if (StringUtils.isBlank(token)) {
            }
            // 从token中获取userId和tenantId
            Integer userId = JWTUtil.getUserId(token);
            String terminalType = JWTUtil.getTerminalType(token);
            if (userId == null || StringUtils.isBlank(terminalType)) {
            }

        }

        return true;
}

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
