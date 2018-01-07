package com.datuzi.supersoft.controller;

import com.datuzi.supersoft.dto.*;
import com.datuzi.supersoft.service.AdmRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangjianbo
 * @date 2017/12/11
 */
@RestController
@RequestMapping("/role")
public class RestRoleController {
    @Autowired
    private AdmRoleService admRoleService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> save(@RequestBody AdmRoleDto dto) {
        return admRoleService.save(dto);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> update(@RequestBody AdmRoleDto dto) {
        return admRoleService.update(dto);
    }

    @RequestMapping(value = "/findByPage",method = RequestMethod.POST)
    @ResponseBody
    public PageResultDto<List<RoleListDto>> findByPage(@RequestBody BasePageDto searchDto) {
        return admRoleService.findByPage(searchDto);
    }

    @RequestMapping(value = "/findById/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<RoleListDto> findById(@PathVariable("id") Long id) {
        return admRoleService.findById(id);
    }

    @RequestMapping(value = "/findAll",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<List<RoleListDto>> findAll(){
        return admRoleService.findAll();
    }

    @RequestMapping(value = "/deleteById",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> deleteById(@RequestBody List<Long> ids) {
        return admRoleService.deleteById(ids);
    }
}
