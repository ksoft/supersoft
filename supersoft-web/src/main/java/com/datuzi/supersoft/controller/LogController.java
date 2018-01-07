package com.datuzi.supersoft.controller;

import com.datuzi.supersoft.controller.base.BaseController;
import com.datuzi.supersoft.dto.*;
import com.datuzi.supersoft.feign.AdmLogFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 27度 on 2018/1/7 0007.
 */
@Controller
@RequestMapping("/log")
public class LogController extends BaseController{
    @Autowired
    private AdmLogFeign admLogFeign;

    /**
     * 列表页
     * @return
     */
    @GetMapping(value = "/index")
    public String index() {
        return "log/list";
    }

    /**
     * 列表
     * @return
     */
    @PostMapping(value = "/list")
    @ResponseBody
    public PageResultDto<List<AdmLogDto>> list(@RequestBody BasePageDto searchDto){
        return admLogFeign.findByPage(searchDto);
    }

    /**
     * 删除
     * @return
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public ResponseDto<Boolean> delete(@RequestBody List<Long> ids) {
        return admLogFeign.deleteById(ids);
    }
}
