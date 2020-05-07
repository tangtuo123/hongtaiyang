package com.hongtaiyang.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hongtaiyang.admin.service.IUserService;
import com.hongtaiyang.common.annotation.Authentication;
import com.hongtaiyang.common.constant.RedisConstant;
import com.hongtaiyang.common.constant.TerminalTypeConstant;
import com.hongtaiyang.common.entity.HttpResponse;
import com.hongtaiyang.common.entity.User;
import com.hongtaiyang.common.entity.dto.UserAdminDTO;
import com.hongtaiyang.common.entity.request.UserRequest;
import com.hongtaiyang.common.enums.SystemCode;
import com.hongtaiyang.common.exception.SysException;
import com.hongtaiyang.common.utils.EncryptUtil;
import com.hongtaiyang.common.utils.JWTUtil;
import com.hongtaiyang.common.utils.RedisUtil;
import com.hongtaiyang.common.validate.InsertGroup;
import com.hongtaiyang.common.validate.UpdateGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/4/24 17:13
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理类")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 登录失败最大次数
     */
    private static final Integer MAX_LOGIN_ERROR_TIMES = 5;

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public HttpResponse login(@Validated @RequestBody UserRequest user, HttpServletResponse response) throws Exception {
        // 首先从redis查询最近输错密码的次数
        Object times = redisUtil.get(RedisConstant.LOGIN_ERROR_TIMES_ADMIN_PREFIX + user.getUserName());
        if (times != null && (Integer) times >= MAX_LOGIN_ERROR_TIMES) {
            throw SysException.asException(SystemCode.LOGIN_ERROR, "您最近半小时已连续输错密码超过5次,系统将锁定半小时,请稍后再试");
        }
        User u = userService.selectByUserName(user.getUserName());
        // 未注册
        if (u == null) {
            throw SysException.asException(SystemCode.LOGIN_ERROR, "您还没有注册");
        }
        // 密码错误
        if (!EncryptUtil.decrypt(u.getPassword()).equals(user.getPassword())) {
            System.out.println(u.getPassword());
            redisUtil.incr(RedisConstant.LOGIN_ERROR_TIMES_ADMIN_PREFIX + user.getUserName(), 1);
            redisUtil.expire(RedisConstant.LOGIN_ERROR_TIMES_ADMIN_PREFIX + user.getUserName(), 30 * 60);
            Integer errorTimes = (Integer) redisUtil.get(RedisConstant.LOGIN_ERROR_TIMES_ADMIN_PREFIX + user.getUserName());
            String errorMsg;
            if (errorTimes < MAX_LOGIN_ERROR_TIMES) {
                errorMsg = String.format("您已连续输错密码%s次,连续输错5次后,系统将锁定30分钟", errorTimes);
            } else {
                errorMsg = String.format("您已连续输错密码5次了,系统将锁定30分钟");
            }
            throw SysException.asException(SystemCode.LOGIN_ERROR, errorMsg);
        }
        // 登录成功,删掉密码输入错误次数的记录
        redisUtil.del(RedisConstant.LOGIN_ERROR_TIMES_ADMIN_PREFIX + user.getUserName());
        String token = JWTUtil.createToken(u.getId().toString(), TerminalTypeConstant.TYPE_ADMIN);
        redisUtil.set(RedisConstant.TOKEN_ADMIN_PREFIX + u.getId(), token, 60 * 60 * 4);
        response.setHeader("Authorization", token);
        return HttpResponse.success(u);
    }

    @Authentication
    @PostMapping(value = "/logout")
    @ApiOperation(value = "退出登录")
    public HttpResponse logout(HttpServletRequest request) {
        // 把当前用户的token从redis里删掉
        redisUtil.del(RedisConstant.TOKEN_ADMIN_PREFIX + JWTUtil.getUserId(request.getHeader("Authorization")));
        return HttpResponse.success(true);
    }

    @Authentication
    @PostMapping(value = "/create")
    @ApiOperation(value = "创建用户")
    public HttpResponse createUser(@Validated(value = InsertGroup.class) @RequestBody User user) {
        // 用户名不能重复
        if (userService.selectByUserName(user.getUserName()) != null) {
            throw SysException.asException(SystemCode.CREATE_ERROR, "用户创建失败,此用户名已存在");
        }
        userService.createUser(user);
        return HttpResponse.success(true);
    }

    @Authentication
    @PutMapping(value = "/update")
    @ApiOperation(value = "修改用户")
    public HttpResponse updateUser(@Validated(value = UpdateGroup.class) @RequestBody User user) {
        userService.updateUser(user);
        return HttpResponse.success(true);
    }

    @Authentication
    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除用户,支持批量删除,删除多个的话id之间用逗号隔开")
    public HttpResponse delete(@PathVariable String id) {
        userService.delete(id);
        return HttpResponse.success(true);
    }

    @Authentication
    @GetMapping(value = "/detail/{id}")
    @ApiOperation(value = "查看详情")
    public HttpResponse getById(@PathVariable Integer id) {
        return HttpResponse.success(userService.getById(id));
    }

    @Authentication
    @GetMapping(value = "/list/{pageNum}/{pageSize}")
    @ApiOperation(value = "查看详情")
    public HttpResponse getUserList(@PathVariable Integer pageNum, @PathVariable Integer pageSize, @RequestParam(required = false) String nickName) {
        Page<UserAdminDTO> page = new Page<>(pageNum, pageSize);
        return HttpResponse.success(userService.getUserList(page, nickName));
    }


}
