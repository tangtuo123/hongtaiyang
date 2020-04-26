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
     * @param page      分页
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    IPage<SysLogDTO> getLogList(Page page, @Param("startTime") String startTime, @Param("endTime") String endTime);

}
