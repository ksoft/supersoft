package com.datuzi.supersoft.feign;

import com.datuzi.supersoft.constant.Constants;
import com.datuzi.supersoft.dto.AdmUserDto;
import com.datuzi.supersoft.dto.LoginUserDto;
import com.datuzi.supersoft.dto.ResponseDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangjianbo
 * @date 2017/12/7
 */
@FeignClient(name= Constants.SERVICE_PROVIDER)
public interface LoginFeign {
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<AdmUserDto> login(@RequestBody LoginUserDto loginUserDto);
}
