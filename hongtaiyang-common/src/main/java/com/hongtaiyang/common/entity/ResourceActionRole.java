package com.hongtaiyang.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/3/16 17:42
 * @description：角色资源菜单操作关联实体类
 * @version: 1.0.0
 */
@Data
@TableName(value = "tb_rel_resource_action_role")
public class ResourceActionRole {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer actionId;
    private Integer resourceId;
    private Integer roleId;
}
