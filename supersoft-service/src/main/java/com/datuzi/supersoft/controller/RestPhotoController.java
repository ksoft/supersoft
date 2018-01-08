package com.datuzi.supersoft.controller;

import com.datuzi.supersoft.dto.*;
import com.datuzi.supersoft.service.AdmRoleService;
import com.datuzi.supersoft.service.UserPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangjianbo
 * @date 2018/1/8
 */
@RestController
@RequestMapping("/photo")
public class RestPhotoController {
    @Autowired
    private UserPhotoService userPhotoService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> save(@RequestBody UserPhotoDto dto) {
        return userPhotoService.save(dto);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> update(@RequestBody UserPhotoDto dto) {
        return userPhotoService.update(dto);
    }

    @RequestMapping(value = "/findByPage",method = RequestMethod.POST)
    @ResponseBody
    public PageResultDto<List<UserPhotoDto>> findByPage(@RequestBody BasePageDto searchDto) {
        return userPhotoService.findByPage(searchDto);
    }

    @RequestMapping(value = "/findById/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<UserPhotoDto> findById(@PathVariable("id") Long id) {
        return userPhotoService.findById(id);
    }

    @RequestMapping(value = "/findAll",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<List<UserPhotoDto>> findAll(){
        return userPhotoService.findAll();
    }

    @RequestMapping(value = "/deleteById",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> deleteById(@RequestBody List<Long> ids) {
        return userPhotoService.deleteById(ids);
    }
}
