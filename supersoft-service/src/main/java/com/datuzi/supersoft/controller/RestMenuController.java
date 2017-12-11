package com.datuzi.supersoft.controller;

import com.datuzi.dto.LeftMenuDto;
import com.datuzi.dto.ResponseDto;
import com.datuzi.dto.TopMenuDto;
import com.datuzi.service.AdmMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 27度 on 2017/12/9 0009.
 */
@RestController
public class RestMenuController {
    @Autowired
    private AdmMenuService admMenuService;

    @RequestMapping(value = "/topMenu",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<List<TopMenuDto>> topMenu(@RequestBody Long roleCode) {
        return admMenuService.topMenu(roleCode);
    }

    @RequestMapping(value = "/leftMenu",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<List<LeftMenuDto>> leftMenu(@RequestBody Long pid) {
        return admMenuService.leftMenu(pid);
    }
}
