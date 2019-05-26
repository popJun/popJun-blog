package com.popjun.sso.cas.server.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * 为了使@Service，@Controller有效
 */
@Configuration
@ComponentScan(basePackages = {"com.popjun.sso.cas.server"})
public class SpringConfig {
}
