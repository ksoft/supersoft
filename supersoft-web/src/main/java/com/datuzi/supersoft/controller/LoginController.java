package com.datuzi.supersoft.controller;

import com.datuzi.supersoft.constant.Constants;
import com.datuzi.supersoft.controller.base.BaseController;
import com.datuzi.supersoft.dto.*;
import com.datuzi.supersoft.feign.AdmLogFeign;
import com.datuzi.supersoft.feign.LoginFeign;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * 登录接口
 * @author zhangjianbo
 * @date 2017/5/11
 */
@Controller
public class LoginController extends BaseController{
    @Autowired
    private DefaultKaptcha defaultKaptcha;
    @Autowired
    private LoginFeign loginFeign;
    @Autowired
    private AdmLogFeign admLogFeign;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 首页
     * @return
     */
    @GetMapping(value = "toLogin")
    public String toLogin() {
        return "login/login";
    }

    /**
     * 退出登录
     * @return
     */
    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        super.clearCurrent(request);
        return "redirect:/toLogin";
    }

    /**
     * 本系统登录
     * @return
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseDto<Boolean> login(LoginDto loginDto, HttpSession session,HttpServletRequest request,HttpServletResponse response){
        String code=session.getAttribute(Constants.SESSION_KEY_KAPTCHA).toString();
        if(StringUtils.isEmpty(code)){
            return ResponseDtoFactory.toError("验证码不能为空");
        }
        if(!code.equals(loginDto.getCode())){
            return ResponseDtoFactory.toError("验证码不正确");
        }
        LoginUserDto loginUserDto=new LoginUserDto();
        BeanUtils.copyProperties(loginDto,loginUserDto);

        ResponseDto<AdmUserDto> result= loginFeign.login(loginUserDto);
        if(result.isSuccess()){
            String token=UUID.randomUUID().toString();
            redisTemplate.opsForValue().set(token, result.getData());
            response.setHeader(Constants.TOKEN,token);
            Cookie cookie=new Cookie(Constants.TOKEN,token);
            response.addCookie(cookie);

            AdmLogDto logDto=new AdmLogDto();
            logDto.setUserId(result.getData().getId());
            logDto.setIp(super.getIP(request));
            logDto.setCreateBy(result.getData().getUserName());
            admLogFeign.save(logDto);
            return ResponseDtoFactory.toSuccess("登录成功",Boolean.TRUE);
        }else{
            return ResponseDtoFactory.toError(result.getMessage(),Boolean.FALSE);
        }
    }

    /**
     * 首页
     * @return
     */
    @GetMapping(value = "/index")
    public String index(Model model) {
        AdmUserDto user=super.getCurrent();
        model.addAttribute("headIcon",user.getHeadIcon());
        return "index/index";
    }

    /**
     * 首页中间
     * @return
     */
    @GetMapping(value = "/main")
    public String main() {
        return "main/main";
    }

    /**
     * 生成验证码
     * @param response
     * @return
     * @throws Exception
     */
    @GetMapping("/img/code")
    public StreamingResponseBody getCaptcha(HttpServletResponse response, HttpSession session) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control",
                "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String code = defaultKaptcha.createText();
        session.setAttribute(Constants.SESSION_KEY_KAPTCHA, code);
        final BufferedImage image = defaultKaptcha.createImage(code);
        return new StreamingResponseBody() {
            @Override
            public void writeTo(OutputStream outputStream) throws IOException {
                ImageIO.write(image, "jpg", outputStream);
            }
        };
    }
}
