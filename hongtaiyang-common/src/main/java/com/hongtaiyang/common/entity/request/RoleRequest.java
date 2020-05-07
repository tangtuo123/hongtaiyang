package com.hongtaiyang.common.entity.request;

import com.hongtaiyang.common.entity.Role;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/3/16 17:31
 * @description：
 * @version: 1.0.0
 */
@Data
public class RoleRequest {
    private Role role;
    @NotEmpty(message = "角色权限不能为空")
    private List<ResourceRequest> resourceMenu;
}
