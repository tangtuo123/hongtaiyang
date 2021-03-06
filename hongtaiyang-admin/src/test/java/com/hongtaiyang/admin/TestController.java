package com.hongtaiyang.admin;

import com.hongtaiyang.common.annotation.Authentication;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/4/24 15:12
 * @description：
 * @version: 1.0.0
 */
@RestController
@RequestMapping(value = "/test")
@Api(tags = "测试类")
public class TestController {

    @Value(value = "${server.port}")
    private String port;

    @GetMapping("/get")
    @Authentication
    @ApiOperation(value = "获取端口号")
    public Map getPort() {
        Map<String, String> map = new HashMap<>();
        map.put("port", port);
        return map;
    }
}
