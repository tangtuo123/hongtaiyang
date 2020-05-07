package com.hongtaiyang.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hongtaiyang.common.entity.base.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/3/16 10:57
 */
@Data
@TableName(value = "tb_role")
public class Role extends BaseEntity {
    @NotBlank(message = "角色名不能为空")
    private String name;
    private String description;

}
