package com.hongtaiyang.common.interceptor;

import com.hongtaiyang.common.annotation.Authentication;
import com.hongtaiyang.common.constant.RedisConstant;
import com.hongtaiyang.common.enums.SystemCode;
import com.hongtaiyang.common.exception.SysException;
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
                throw SysException.asException(SystemCode.AUTH_ERROR, "请先登录");
            }
            // 从请求路径中获取terminalType
            String parameter = request.getParameter("terminalType");
            // 从token中获取userId和terminalType
            Integer userId = JWTUtil.getUserId(token);
            String terminalType = JWTUtil.getTerminalType(token);
            if (userId == null || StringUtils.isBlank(terminalType)) {
                throw SysException.asException(SystemCode.AUTH_ERROR, "token解析有误");
            }
            if (!terminalType.equals(parameter)) {
                throw SysException.asException(SystemCode.AUTH_ERROR, "终端类型有误");
            }
            String tokenPrefix = RedisConstant.getTokenPrefixByTerminalType(terminalType);
            // 从redis中获取token
            String redisToken = (String) redisUtil.get(tokenPrefix + userId);
            if (!token.equals(redisToken)) {
                throw SysException.asException(SystemCode.AUTH_ERROR, "您的账号已在别处登录,请重新登录");
            }
            if (JWTUtil.isExpire(token)) {
                // token已过期,但还在登录保质期内,返回刷新后的token,重新发起请求
                if (StringUtils.isNotBlank(redisToken)) {
                    String refreshToken = JWTUtil.createToken(userId.toString(), terminalType);
                    // 获取token的失效时长,token失效时长的单位是毫秒 redis的过期时间是秒,所以需要除以1000
                    Long expireTime = JWTUtil.getExpireTimeByTerminalType(terminalType) / 1000L;
                    // 把刷新后的token存到redis里,设置过期时间为token失效时长的两倍
                    redisUtil.set(tokenPrefix + userId, refreshToken, 2 * expireTime);
                    throw SysException.asException(SystemCode.REQUEST_ERROR, refreshToken);
                } else {
                    // 重新登录
                    throw SysException.asException(SystemCode.AUTH_ERROR, "登录已失效,请重新登录");
                }
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
