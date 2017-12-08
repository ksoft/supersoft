package com.datuzi.supersoft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * 配置国际化语言文件
 * @author zhangjianbo
 * @date 2017/12/6
 */
@Configuration
public class AppConfig {
    @Bean(name="localeResolver")
    public LocaleResolver localeResolverBean() {
        return new SessionLocaleResolver();
    }

    @Bean(name="messageSource")
    public ResourceBundleMessageSource resourceBundleMessageSource(){
        ResourceBundleMessageSource source=new ResourceBundleMessageSource();
        source.setBasename("supersoft");//国际化文件以supersoft开头
        return source;
    }
}
