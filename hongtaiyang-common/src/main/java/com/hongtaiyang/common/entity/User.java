package com.hongtaiyang.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/4/24 17:05
 * @description：
 * @version: 1.0.0
 */
@Data
@TableName(value = "tb_user")
public class User {

    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    @Length(max = 12, message = "用户名长度不能超过12")
    private String userName;
    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;
}
