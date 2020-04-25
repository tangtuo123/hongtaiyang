package com.hongtaiyang.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/3/16 12:45
 */
@Data
@TableName(value = "tb_action_info")
public class Action {
    private Integer actionId;
    private String actionName;
    private String actionLabel;
}
