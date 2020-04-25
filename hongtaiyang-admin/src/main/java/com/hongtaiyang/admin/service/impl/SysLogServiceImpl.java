package com.hongtaiyang.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongtaiyang.admin.mapper.SysLogMapper;
import com.hongtaiyang.admin.service.ISysLogService;
import com.hongtaiyang.common.entity.SysLog;
import com.hongtaiyang.common.entity.dto.SysLogDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/4/25 13:40
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public IPage<SysLogDTO> getLogList(Page page, String startTime, String endTime) {
        return sysLogMapper.getLogList(page, startTime, endTime);
    }
}
