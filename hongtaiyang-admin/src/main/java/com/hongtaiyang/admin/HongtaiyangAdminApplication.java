package com.hongtaiyang.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = {"com.hongtaiyang.admin","com.hongtaiyang.common"})
public class HongtaiyangAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(HongtaiyangAdminApplication.class, args);
    }

}
