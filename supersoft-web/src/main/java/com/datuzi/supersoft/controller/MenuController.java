package com.datuzi.supersoft.controller;

import com.datuzi.supersoft.controller.base.BaseController;
import com.datuzi.supersoft.dto.*;
import com.datuzi.supersoft.feign.AdmMenuFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 27度 on 2017/12/9 0009.
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {
    @Autowired
    private AdmMenuFeign admMenuFeign;

    /**
     * 本顶部菜单
     * @return
     */
    @PostMapping(value = "/topMenu")
    @ResponseBody
    public ResponseDto<List<TopMenuDto>> topMenu(){
        ResponseDto<List<TopMenuDto>> responseDto= admMenuFeign.topMenu(1L);
        return ResponseDtoFactory.toSuccess(responseDto.getData());
    }

    /**
     * 根据顶部菜单，获取左侧菜单
     * @return
     */
    @PostMapping(value = "/leftMenuByTopMenu/{pid}")
    @ResponseBody
    public ResponseDto<List<LeftMenuDto>> leftMenuByTopMenu(@PathVariable("pid") Long pid) {
        ResponseDto<List<LeftMenuDto>> responseDto = admMenuFeign.leftMenu(pid);
        return ResponseDtoFactory.toSuccess(responseDto.getData());
    }
}
