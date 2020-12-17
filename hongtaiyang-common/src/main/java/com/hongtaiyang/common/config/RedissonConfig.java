package com.hongtaiyang.common.config;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/12/13 17:48
 */
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redisson() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").
                setPassword("123456");
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }
}
