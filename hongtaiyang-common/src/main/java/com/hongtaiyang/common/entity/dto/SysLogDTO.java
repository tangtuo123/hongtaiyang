package com.hongtaiyang.common.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/4/25 19:10
 */
@Data
public class SysLogDTO {
    private String username;
    private Integer id;
    private String ip;
    private String module;
    private String method;
    private Date createDate;

}
