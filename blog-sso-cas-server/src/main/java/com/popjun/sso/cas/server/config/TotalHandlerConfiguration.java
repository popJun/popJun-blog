package com.popjun.sso.cas.server.config;

import com.popjun.sso.cas.server.custom.handler.TotalHandler;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlan;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlanConfigurer;
import org.apereo.cas.authentication.principal.DefaultPrincipalFactory;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("totalHandlerConfiguration")
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class TotalHandlerConfiguration implements AuthenticationEventExecutionPlanConfigurer {
    @Autowired
    @Qualifier("servicesManager")
    private ServicesManager servicesManager;
    @Override
    public void configureAuthenticationExecutionPlan(AuthenticationEventExecutionPlan plan) {
        plan.registerAuthenticationHandler(totalHandler());
    }

    @Bean
    public TotalHandler totalHandler(){
        TotalHandler handler = new TotalHandler(TotalHandler.class.getName(), servicesManager, new DefaultPrincipalFactory(),1);
        return handler;
    }
}
