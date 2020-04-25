package com.hongtaiyang.admin.controller;

import com.hongtaiyang.admin.service.IUserService;
import com.hongtaiyang.common.constant.RedisConstant;
import com.hongtaiyang.common.constant.TerminalTypeConstant;
import com.hongtaiyang.common.entity.HttpResponse;
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

/**
 * @author ：tangtuo
 * @date ：Created in 2020/4/24 17:13
 * @description：
 * @version: 1.0.0
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理类")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public HttpResponse login(@Validated @RequestBody User user, HttpServletResponse response) {
        User u = userService.selectByUserName(user.getUserName());
        // 未注册
        if (u == null) {
            throw SysException.asException(SystemCode.LOGIN_ERROR, "您还没有注册");
        }
        // 密码错误
        if (!u.getPassword().equals(user.getPassword())) {
            throw SysException.asException(SystemCode.LOGIN_ERROR, "密码错误");
        }
        String token = JWTUtil.createToken(u.getId().toString(), TerminalTypeConstant.TYPE_ADMIN);
        redisUtil.set(RedisConstant.TOKEN_ADMIN_PREFIX + u.getId(), token, 60 * 60 * 4);
        response.setHeader("Authorization", token);
        return HttpResponse.success(user);
    }
}
