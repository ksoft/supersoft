package com.datuzi.supersoft.controller;

import com.datuzi.supersoft.dto.AdmLogDto;
import com.datuzi.supersoft.dto.BasePageDto;
import com.datuzi.supersoft.dto.PageResultDto;
import com.datuzi.supersoft.dto.ResponseDto;
import com.datuzi.supersoft.service.AdmLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 27度 on 2018/1/7 0007.
 */
@RestController
@RequestMapping("/log")
public class RestLogController {
    @Autowired
    private AdmLogService admLogService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> save(@RequestBody AdmLogDto dto) {
        return admLogService.save(dto);
    }

    @RequestMapping(value = "/findByPage",method = RequestMethod.POST)
    @ResponseBody
    public PageResultDto<List<AdmLogDto>> findByPage(@RequestBody BasePageDto searchDto) {
        return admLogService.findByPage(searchDto);
    }

    @RequestMapping(value = "/deleteById",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> deleteById(@RequestBody List<Long> ids) {
        return admLogService.deleteById(ids);
    }
}
