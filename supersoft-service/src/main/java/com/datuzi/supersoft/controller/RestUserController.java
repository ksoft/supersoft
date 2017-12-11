package com.datuzi.supersoft.controller;

import com.datuzi.supersoft.dto.AdmUserDto;
import com.datuzi.supersoft.dto.ResponseDto;
import com.datuzi.supersoft.service.AdmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangjianbo
 * @date 2017/12/11
 */
@RestController
@RequestMapping("/user")
public class RestUserController {
    @Autowired
    private AdmUserService admUserService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> save(@RequestBody AdmUserDto admUserDto) {
        return admUserService.saveAdmUser(admUserDto);
    }
}
