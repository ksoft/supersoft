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
    public ResponseDto<List<TopMenuDto>> topMenu(@RequestBody Long roleId) {
        return admMenuService.topMenu(roleId);
    }

    @RequestMapping(value = "/leftMenu",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<List<LeftMenuDto>> leftMenu(@RequestBody LeftMenuSearchDto searchDto) {
        return admMenuService.leftMenu(searchDto);
    }

    @RequestMapping(value = "/findMenuTree",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<List<MenuTreeDto>> findMenuTree(@RequestBody Long roleId) {
        return admMenuService.findMenuTree(roleId);
    }

    @RequestMapping(value = "/findByPage",method = RequestMethod.POST)
    @ResponseBody
    public PageResultDto<List<AdmMenuDto>> findByPage(@RequestBody BasePageDto searchDto) {
        return admMenuService.findByPage(searchDto);
    }

    @RequestMapping(value = "/deleteById",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> deleteById(@RequestBody List<Long> ids) {
        return admMenuService.deleteById(ids);
    }

    @RequestMapping(value = "/findAll",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<List<AdmMenuDto>> findAll(){
        return admMenuService.findAll();
    }

    @RequestMapping(value = "/findById/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<AdmMenuDto> findById(@PathVariable("id") Long id) {
        return admMenuService.findById(id);
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
