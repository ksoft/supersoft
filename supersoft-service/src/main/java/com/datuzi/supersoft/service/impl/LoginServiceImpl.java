package com.datuzi.supersoft.service.impl;

import com.datuzi.dto.LoginUserDto;
import com.datuzi.dto.ResponseDto;
import com.datuzi.dto.ResponseDtoFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangjianbo
 * @date 2017/12/7
 */
@RestController
public class LoginServiceImpl {
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> login(@RequestBody LoginUserDto loginUserDto) {
        return ResponseDtoFactory.toSuccess(Boolean.TRUE);
    }
}
