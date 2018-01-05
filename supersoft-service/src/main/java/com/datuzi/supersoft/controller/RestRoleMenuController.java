package com.datuzi.supersoft.controller;

import com.datuzi.supersoft.dto.AdmRoleMenuDto;
import com.datuzi.supersoft.dto.ResponseDto;
import com.datuzi.supersoft.service.AdmRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangjianbo
 * @date 2018/1/5
 */
@RestController
@RequestMapping("/roleMenu")
public class RestRoleMenuController {
    @Autowired
    private AdmRoleMenuService admRoleMenuService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> save(@RequestBody List<AdmRoleMenuDto> roleMenuDtoList) {
        return admRoleMenuService.save(roleMenuDtoList);
    }
}
