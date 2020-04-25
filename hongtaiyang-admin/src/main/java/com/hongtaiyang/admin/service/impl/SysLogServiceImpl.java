package com.hongtaiyang.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongtaiyang.admin.mapper.SysLogMapper;
import com.hongtaiyang.admin.service.ISysLogService;
import com.hongtaiyang.common.entity.SysLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/4/25 13:40
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {
}
