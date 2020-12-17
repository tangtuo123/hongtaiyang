package com.hongtaiyang.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/12/16 14:18
 */
@Configuration
@EnableConfigurationProperties(value = CacheProperties.class)
public class RedisCacheConfig {

    @Autowired
    private CacheProperties cacheProperties;

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        if (cacheProperties.getRedis().getTimeToLive() != null) {
            redisCacheConfiguration = redisCacheConfiguration.entryTtl(cacheProperties.getRedis().getTimeToLive());
        }
        return redisCacheConfiguration;

    }
}
