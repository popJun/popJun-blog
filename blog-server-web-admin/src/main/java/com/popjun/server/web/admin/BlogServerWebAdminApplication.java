package com.popjun.server.web.admin;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.cas.starter.config.EnableCasClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableDubboConfiguration
@EnableCasClient
public class BlogServerWebAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogServerWebAdminApplication.class, args);
    }

}
