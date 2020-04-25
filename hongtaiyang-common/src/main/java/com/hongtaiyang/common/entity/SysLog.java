package com.hongtaiyang.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.omg.CORBA.IDLType;

import java.util.Date;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/4/25 13:34
 */
@Data
@TableName("tb_sys_log")
public class SysLog {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private String ip;
    private String module;
    private String method;
    private Date createDate;
}
