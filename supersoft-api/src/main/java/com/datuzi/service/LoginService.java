package com.datuzi.service;

import com.datuzi.dto.LoginUserDto;
import com.datuzi.dto.ResponseDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangjianbo
 * @date 2017/12/7
 */
@FeignClient(name= "supersoft-provider")
public interface LoginService {
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<Boolean> login(@RequestBody LoginUserDto loginUserDto);
}
