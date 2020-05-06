package com.hongtaiyang.common.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hongtaiyang.common.validate.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/3/5 14:29
 * @description：
 * @version: 1.0.0
 */
@Data
public class BaseEntity {

    @TableId(type = IdType.AUTO)
    @NotNull(message = "主键不能为空", groups = {UpdateGroup.class})
    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
    @JsonIgnore
    @ApiModelProperty(name = "是否删除", notes = "0:未删除   1:已删除")
    private Integer delFlag;
}
