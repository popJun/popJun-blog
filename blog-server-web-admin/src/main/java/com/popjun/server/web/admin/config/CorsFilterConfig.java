package com.popjun.server.web.admin.config;

import com.popjun.utils.CorsUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Configuration
@Order(value=0)
@WebFilter(filterName = "CorsFilterConfig", urlPatterns = "/*")
public class CorsFilterConfig implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
       CorsUtil.addCORS(servletRequest,servletResponse,filterChain);
    }

    @Override
    public void destroy() {

    }
  }
