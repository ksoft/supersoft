package com.datuzi.supersoft.controller;

import com.datuzi.dto.LoginUserDto;
import com.datuzi.dto.ResponseDto;
import com.datuzi.dto.ResponseDtoFactory;
import com.datuzi.service.AdmUserService;
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
    public ResponseDto<Boolean> login(@RequestBody LoginUserDto loginUserDto) {
        Boolean flag=admUserService.findAdmUserByUserCodeAndPassword(loginUserDto.getUserCode(),loginUserDto.getPassword());
        if(flag){
            return ResponseDtoFactory.toSuccess(Boolean.TRUE);
        }
        return ResponseDtoFactory.toSuccess(Boolean.FALSE);
    }
}
