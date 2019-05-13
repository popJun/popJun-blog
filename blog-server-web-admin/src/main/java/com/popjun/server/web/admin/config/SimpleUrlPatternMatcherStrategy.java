package com.popjun.server.web.admin.config;

import org.jasig.cas.client.authentication.UrlPatternMatcherStrategy;

import java.util.regex.Pattern;

/**
 * 用于自定义鉴权路径
 *
 */
public class SimpleUrlPatternMatcherStrategy implements UrlPatternMatcherStrategy{
    private Pattern pattern;
    @Override
    public boolean matches(String url) {
        if (url.contains("/insert")){
            return true;
        }
        return this.pattern.matcher(url).find();
    }

    @Override
    public void setPattern(String pattern) {
        this.pattern =Pattern.compile(pattern);
    }
}
