package com.datuzi.supersoft.controller;

import com.datuzi.supersoft.dto.AdmUserDto;
import com.datuzi.supersoft.dto.LoginUserDto;
import com.datuzi.supersoft.dto.ResponseDto;
import com.datuzi.supersoft.service.AdmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangjianbo
 * @date 2017/12/7
 */
@RestController
public class RestLoginController {
    @Autowired
    private AdmUserService admUserService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<AdmUserDto> login(@RequestBody LoginUserDto loginUserDto) {
        return admUserService.findOne(loginUserDto);
    }
}
