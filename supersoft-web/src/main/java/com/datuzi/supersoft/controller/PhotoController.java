package com.datuzi.supersoft.controller;

import com.datuzi.supersoft.controller.base.BaseController;
import com.datuzi.supersoft.dto.*;
import com.datuzi.supersoft.feign.UserPhotoFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangjianbo
 * @date 2018/1/8
 */
@Controller
@RequestMapping("/photo")
public class PhotoController extends BaseController{
    @Autowired
    private UserPhotoFeign userPhotoFeign;

    /**
     * 列表页
     * @return
     */
    @GetMapping(value = "/index")
    public String index() {
        return "photo/list";
    }

    /**
     * 删除
     * @return
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public ResponseDto<Boolean> delete(@RequestBody List<Long> ids) {
        return userPhotoFeign.deleteById(ids);
    }

    /**
     * 列表
     * @return
     */
    @PostMapping(value = "/list")
    @ResponseBody
    public PageResultDto<List<UserPhotoDto>> list(@RequestBody BasePageDto searchDto){
        return userPhotoFeign.findByPage(searchDto);
    }

}
