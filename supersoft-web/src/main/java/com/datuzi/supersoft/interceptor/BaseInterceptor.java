package com.datuzi.supersoft.interceptor;

import com.alibaba.fastjson.JSON;
import com.datuzi.supersoft.constant.Constants;
import com.datuzi.supersoft.dto.AdmUserDto;
import com.datuzi.supersoft.dto.ResponseDtoFactory;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

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
        if(!StringUtils.isEmpty(token)){
            AdmUserDto user=(AdmUserDto)redisTemplate.opsForValue().get(token);
            if(user!=null){
                return true;
            }else{
                timeout(response);
            }
        }else{
            timeout(response);
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private void timeout(HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("UTF-8");
        response.setContentType(ContentType.APPLICATION_JSON.toString());
        PrintWriter out = response.getWriter();
        response.setStatus(HttpServletResponse.SC_OK);
        out.print(JSON.toJSON(ResponseDtoFactory.unAuthorized("登陆超时，请重新登陆")));
        out.flush();
        out.close();
    }
}
