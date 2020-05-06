package com.hongtaiyang.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hongtaiyang.common.entity.base.BaseEntity;
import com.hongtaiyang.common.utils.EncryptUtil;
import com.hongtaiyang.common.validate.InsertGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/4/24 17:05
 */
@Data
@TableName(value = "tb_user_admin")
public class User extends BaseEntity {

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空", groups = InsertGroup.class)
    @Length(max = 12, message = "用户名长度不能超过12")
    private String userName;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空", groups = InsertGroup.class)
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "角色id")
    @NotNull(message = "角色id不能为空", groups = InsertGroup.class)
    private Integer roleId;

}
