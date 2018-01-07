package com.datuzi.supersoft.controller.base;

import com.datuzi.supersoft.constant.Constants;
import com.datuzi.supersoft.dto.AdmUserDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangjianbo
 * @date 2017/12/7
 */
public class BaseController {
    @Value("")
    private String eurekaUrl;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取当前用户
     * @return
     */
    public AdmUserDto getCurrent(){
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            String token=request.getHeader(Constants.TOKEN);
            if (StringUtils.isEmpty(token)) {
                Cookie[] cookies=request.getCookies();
                if(cookies!=null){
                    for(Cookie cookie:cookies){
                        if(Constants.TOKEN.equals(cookie.getName())){
                            token=cookie.getValue();
                            break;
                        }
                    }
                }
            }
            if(StringUtils.isNotEmpty(token)){
                return (AdmUserDto)redisTemplate.opsForValue().get(token);
            }
        }
        return null;
    }

    /**
     * 刷新当前用户缓存
     * @param request
     */
    public void refreshCurrent(HttpServletRequest request,AdmUserDto userDto){
        String token=request.getHeader(Constants.TOKEN);
        if(org.springframework.util.StringUtils.isEmpty(token)){
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
        redisTemplate.opsForValue().set(token,userDto);
    }

    /**
     * 清空当前用户缓存
     * @param request
     */
    public void clearCurrent(HttpServletRequest request){
        String token=request.getHeader(Constants.TOKEN);
        if(org.springframework.util.StringUtils.isEmpty(token)){
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
        redisTemplate.delete(token);
    }

    public static String getIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (!checkIP(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (!checkIP(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (!checkIP(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    private static boolean checkIP(String ip) {
        if (ip == null || ip.length() == 0 || "unkown".equalsIgnoreCase(ip)
                || ip.split(".").length != 4) {
            return false;
        }
        return true;
    }
}
