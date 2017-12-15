package com.datuzi.supersoft.controller;

import com.datuzi.supersoft.dto.*;
import com.datuzi.supersoft.feign.AdmUserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
     * 列表页
     * @return
     */
    @GetMapping(value = "index")
    public String main() {
        return "user/list";
    }

    /**
     * 列表
     * @return
     */
    @PostMapping(value = "/list")
    @ResponseBody
    public PageResultDto<List<UserListDto>> list(@RequestBody UserSearchDto searchDto){
        return admUserFeign.findUserPage(searchDto);
    }
}
