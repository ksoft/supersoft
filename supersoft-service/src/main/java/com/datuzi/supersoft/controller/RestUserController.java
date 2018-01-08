package com.datuzi.supersoft.controller;

import com.datuzi.supersoft.dto.*;
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
        return admUserService.save(admUserDto);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<AdmUserDto> update(@RequestBody AdmUserDto admUserDto) {
        return admUserService.update(admUserDto);
    }

    @RequestMapping(value = "/findByPage",method = RequestMethod.POST)
    @ResponseBody
    public PageResultDto<List<AdmUserDto>> findByPage(@RequestBody BasePageDto searchDto) {
        return admUserService.findByPage(searchDto);
    }

    @RequestMapping(value = "/findById/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<AdmUserDto> findById(@PathVariable("id") Long id) {
        return admUserService.findById(id);
    }

    @RequestMapping(value = "/deleteById",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> deleteById(@RequestBody List<Long> ids) {
        return admUserService.deleteById(ids);
    }
}
