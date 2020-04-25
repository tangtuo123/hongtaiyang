package com.hongtaiyang.common.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/2/21 10:54
 * @description：
 * @version: 1.0.0
 */
@Configuration
@ConditionalOnClass(value = {PaginationInterceptor.class})
public class MybatisplusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
}
