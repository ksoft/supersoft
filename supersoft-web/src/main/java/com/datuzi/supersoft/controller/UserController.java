package com.datuzi.supersoft.controller;

import com.datuzi.supersoft.dto.ResponseDto;
import com.datuzi.supersoft.dto.ResponseDtoFactory;
import com.datuzi.supersoft.feign.AdmUserFeign;
import com.datuzi.supersoft.dto.UserListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zhangjianbo
 * @date 2017/12/11
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AdmUserFeign admUserFeign;
    /**
     * 列表
     * @return
     */
    @PostMapping(value = "/list")
    @ResponseBody
    public ResponseDto<List<UserListDto>> topMenu(){
        ResponseDto<List<UserListDto>> responseDto=null;
        return ResponseDtoFactory.toSuccess(responseDto.getData());
    }
}
