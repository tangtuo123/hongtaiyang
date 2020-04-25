package com.hongtaiyang.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hongtaiyang.common.entity.SysLog;
import com.hongtaiyang.common.entity.dto.SysLogDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface SysLogMapper extends BaseMapper<SysLog> {

    /**
     * 列表
     *
     * @param page
     * @param startTime
     * @param endTime
     * @return
     */
    IPage<SysLogDTO> getLogList(Page page, @Param("startTime") String startTime, @Param("endTime") String endTime);

}
