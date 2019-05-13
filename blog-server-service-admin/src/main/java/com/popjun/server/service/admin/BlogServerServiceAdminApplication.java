package com.popjun.server.service.admin;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableDubboConfiguration
@MapperScan("com.popjun.server.admin.persist.dao")
@EnableCaching
public class BlogServerServiceAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogServerServiceAdminApplication.class, args);
    }

}
