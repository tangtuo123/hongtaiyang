package com.hongtaiyang.common.entity.dto;

import com.hongtaiyang.common.entity.Role;
import lombok.Data;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/3/16 20:21
 */
@Data
public class RoleDTO extends Role {
    private String resourceMenu;
}
