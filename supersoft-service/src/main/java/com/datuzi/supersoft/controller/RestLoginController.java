package com.datuzi.supersoft.controller;

import com.datuzi.dto.LoginUserDto;
import com.datuzi.dto.ResponseDto;
import com.datuzi.dto.ResponseDtoFactory;
import com.datuzi.supersoft.dao.AdmUserRepository;
import com.datuzi.supersoft.entity.AdmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangjianbo
 * @date 2017/12/7
 */
@RestController
public class RestLoginController {
    @Autowired
    private AdmUserRepository admUserRepository;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> login(@RequestBody LoginUserDto loginUserDto) {
        AdmUser user=admUserRepository.findAdmUserByUserNameAndPassword(loginUserDto.getUserName(),loginUserDto.getPassword());
        if(user!=null){
            return ResponseDtoFactory.toSuccess(Boolean.TRUE);
        }
        return ResponseDtoFactory.toSuccess(Boolean.FALSE);
    }
}
