package com.hongtaiyang.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/4/24 17:05
 * @description：
 * @version: 1.0.0
 */
@Data
@TableName(value = "user")
public class User {

    private Integer id;
    private String userName;
    private String password;
}
