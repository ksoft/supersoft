package com.datuzi.supersoft.controller;

import com.datuzi.supersoft.dto.*;
import com.datuzi.supersoft.service.AdmMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 27度 on 2017/12/9 0009.
 */
@RestController
@RequestMapping("/menu")
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

    @RequestMapping(value = "/findByPage",method = RequestMethod.POST)
    @ResponseBody
    public PageResultDto<List<MenuListDto>> findByPage(@RequestBody BasePageDto searchDto) {
        return admMenuService.findByPage(searchDto);
    }

    @RequestMapping(value = "/deleteById",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> deleteById(@RequestBody List<Long> ids) {
        return admMenuService.deleteById(ids);
    }

    @RequestMapping(value = "/findAll",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<List<MenuListDto>> findAll(){
        return admMenuService.findAll();
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> save(@RequestBody AdmMenuDto dto) {
        return admMenuService.save(dto);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> update(@RequestBody AdmMenuDto dto) {
        return admMenuService.update(dto);
    }
}
