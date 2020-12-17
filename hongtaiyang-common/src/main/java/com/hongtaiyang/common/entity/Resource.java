package com.hongtaiyang.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/3/16 12:42
 * @description：
 * @version: 1.0.0
 */
@Data
@TableName(value = "tb_resource_info")
public class Resource implements Serializable {
    private Integer id;
    private String name;
    private String label;
    private Integer pid;
    private Integer level;
    private Integer sort;
    private String path;
    private String meta;

    private List<Action> actionList;
    private List<Resource> children;
}
