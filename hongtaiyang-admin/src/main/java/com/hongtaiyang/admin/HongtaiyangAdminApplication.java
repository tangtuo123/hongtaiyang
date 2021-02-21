package com.hongtaiyang.admin;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@EnableRabbit
@EnableCaching
@SpringBootApplication
@ComponentScan(value = {"com.hongtaiyang.admin","com.hongtaiyang.common"})
public class HongtaiyangAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(HongtaiyangAdminApplication.class, args);
    }

}
