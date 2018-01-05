
package com.datuzi.supersoft.config;
import com.datuzi.supersoft.interceptor.ApiInterceptor;
import com.datuzi.supersoft.interceptor.BaseInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author zhangjianbo
 * @since 2017-04-10
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private BaseInterceptor baseInterceptor;
    @Autowired
    private ApiInterceptor apiInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(baseInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/","/docs","/toLogin","/login","/img/code","/404","/401","/500");
        registry.addInterceptor(apiInterceptor).addPathPatterns("/api/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController( "/" ).setViewName( "redirect:/toLogin" );
        registry.addRedirectViewController("/docs","/swagger-ui.html");
        registry.addViewController("/404").setViewName("common/404");
        registry.addViewController("/401").setViewName("common/401");
        registry.addViewController("/500").setViewName("common/500");
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers( registry );
    }

}
