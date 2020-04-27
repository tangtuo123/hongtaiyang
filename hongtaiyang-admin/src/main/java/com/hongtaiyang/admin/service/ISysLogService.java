package com.hongtaiyang.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hongtaiyang.common.entity.SysLog;
import com.hongtaiyang.common.entity.dto.SysLogDTO;


/**
 * @author ：tangtuo
 * @date ：Created in 2020/4/25 13:39
 */
public interface ISysLogService extends IService<SysLog> {

    IPage<SysLogDTO> getLogList(Page page, String startTime, String endTime);
}
