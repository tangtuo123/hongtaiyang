package com.hongtaiyang.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hongtaiyang.common.entity.base.BaseEntity;
import lombok.Data;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/3/16 10:57
 * @description：角色实体类
 * @version: 1.0.0
 */
@Data
@TableName(value = "tb_role")
public class Role extends BaseEntity {
    private String name;
    private Integer type;
    private String description;
    private Integer status;

}
