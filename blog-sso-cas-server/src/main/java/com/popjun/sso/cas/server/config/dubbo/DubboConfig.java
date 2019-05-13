package com.popjun.sso.cas.server.config.dubbo;

import com.alibaba.dubbo.config.*;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDubboConfiguration
public class DubboConfig {
    // 以下信息将从配置文件application.properties中读取
    @Value("${dubbo_registry_address}")
    private String registryAddress;
    @Value("${dubbo_port}")
    private int dubboPort;
    @Value("${dubbo_register}")
    private Boolean dubboRegister;
    // 应用名称
    public static final String APPLICATION_NAME = "sso-service";
    /**
     * 提供方应用信息，用于计算依赖关系
     *
     * @return*/
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(APPLICATION_NAME);
        return applicationConfig;
    }

    /**
     * 使用zookeeper注册中心暴露服务地址
     *
     * @return*/

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(registryAddress);
        registryConfig.setRegister(dubboRegister);
        return registryConfig;
    }

   /**
     * 用dubbo协议在指定端口暴露服务
     *
     * @return*/
    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig("dubbo", dubboPort);
        // 默认为hessian2,但不支持无参构造函数类,而这种方式的效率很低
        protocolConfig.setSerialization("java");
        return protocolConfig;
    }

   /**
     * 监控中心配置，protocol="registry"，表示从注册中心发现监控中心地址
     *
     * @return*/

    @Bean
    public MonitorConfig monitorConfig() {
        MonitorConfig monitorConfig = new MonitorConfig();
        monitorConfig.setProtocol("registry");
        return monitorConfig;
    }

   /**
     * 当ProtocolConfig和ServiceConfig某属性没有配置时,采用此缺省值
     *
     * @return*/

    @Bean
    public ProviderConfig providerConfig() {
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setTimeout(10000);
        providerConfig.setThreadpool("fixed");
        providerConfig.setThreads(100);
        providerConfig.setAccepts(1000);
        return providerConfig;
    }

}
