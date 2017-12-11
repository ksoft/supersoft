package com.datuzi.supersoft.interceptor;

import com.alibaba.fastjson.JSON;
import com.datuzi.constant.Constants;
import com.datuzi.dto.ResponseDtoFactory;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.tags.form.TagWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @author zhangjianbo
 * @since 2017-04-10
 */
@Component
public class BaseInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseInterceptor.class);

    @Value("${spring.profiles.active}")
    private String profile;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader(Constants.TOKEN);
        if(StringUtils.isEmpty(token)){
            Cookie[] cookies=request.getCookies();
            if(cookies!=null && cookies.length>0){
                for(Cookie cookie:cookies){
                    if(Constants.TOKEN.equals(cookie.getName())){
                        token=cookie.getValue();
                        break;
                    }
                }
            }
        }
        Object serverToken=redisTemplate.opsForValue().get(Constants.TOKEN);
        if(!StringUtils.isEmpty(token) && !StringUtils.isEmpty(serverToken) && token.equals(serverToken.toString())){
            return true;
        }else{
            response.setCharacterEncoding("UTF-8");
            response.setContentType(ContentType.APPLICATION_JSON.toString());
            PrintWriter out = response.getWriter();
            response.setStatus(HttpServletResponse.SC_OK);
            out.print(JSON.toJSON(ResponseDtoFactory.unAuthorized("登陆超时，请重新登陆")));
            out.flush();
            out.close();
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
