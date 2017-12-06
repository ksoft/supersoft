package com.datuzi.supersoft.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ApiInterceptor
 * API 请求拦截器
 * @author HZH
 * @date 2017/4/25
 */
@Component
public class ApiInterceptor implements HandlerInterceptor {

    /** 默认忽略URL */
    private static final String[] DEFAULT_IGNORE_URL_PATTERNS = new String[] {"/api/**" };

    /** 忽略URL */
    private String[] ignoreUrlPatterns = DEFAULT_IGNORE_URL_PATTERNS;

    /** antPathMatcher */
    private static AntPathMatcher antPathMatcher = new AntPathMatcher();



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String path = request.getServletPath();
        boolean isIgnore = true;
        for (String ignoreUrl : ignoreUrlPatterns) {
            if (antPathMatcher.match(ignoreUrl, path)) {
                isIgnore = false;
                break;
            }
        }
        if (isIgnore) {

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
