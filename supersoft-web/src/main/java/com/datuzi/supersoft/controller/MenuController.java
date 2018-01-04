package com.datuzi.supersoft.controller;

import com.datuzi.supersoft.controller.base.BaseController;
import com.datuzi.supersoft.dto.*;
import com.datuzi.supersoft.feign.AdmMenuFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 27度 on 2017/12/9 0009.
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {
    @Autowired
    private AdmMenuFeign admMenuFeign;

    /**
     * 顶部菜单
     * @return
     */
    @PostMapping(value = "/topMenu")
    @ResponseBody
    public ResponseDto<List<TopMenuDto>> topMenu(){
        AdmUserDto admUserDto=getCurrent();
        ResponseDto<List<TopMenuDto>> responseDto= admMenuFeign.topMenu(admUserDto.getRoleId());
        return ResponseDtoFactory.toSuccess(responseDto.getData());
    }

    /**
     * 根据顶部菜单，获取左侧菜单
     * @return
     */
    @PostMapping(value = "/leftMenuByTopMenu/{pid}")
    @ResponseBody
    public ResponseDto<List<LeftMenuDto>> leftMenuByTopMenu(@PathVariable("pid") Long pid) {
        AdmUserDto admUserDto=getCurrent();
        LeftMenuSearchDto searchDto=new LeftMenuSearchDto(admUserDto.getRoleId(),pid);
        ResponseDto<List<LeftMenuDto>> responseDto = admMenuFeign.leftMenu(searchDto);
        return ResponseDtoFactory.toSuccess(responseDto.getData());
    }

    /**
     * 列表页
     * @return
     */
    @GetMapping(value = "/index")
    public String index() {
        return "menu/list";
    }

    /**
     * 新增
     * @return
     */
    @GetMapping(value = "/add")
    public String add(Model model) {
        ResponseDto<List<MenuListDto>> responseDto=admMenuFeign.findAll();
        model.addAttribute("menus",responseDto.getData());
        return "menu/add";
    }

    /**
     * 编辑页
     * @return
     */
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        ResponseDto<List<MenuListDto>> responseDto=admMenuFeign.findAll();
        model.addAttribute("menus",responseDto.getData());
        ResponseDto<MenuListDto> dto=admMenuFeign.findById(id);
        model.addAttribute("menu",dto.getData());
        return "menu/edit";
    }

    /**
     * 查看页
     * @return
     */
    @GetMapping(value = "/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        ResponseDto<List<MenuListDto>> responseDto=admMenuFeign.findAll();
        model.addAttribute("menus",responseDto.getData());
        ResponseDto<MenuListDto> dto=admMenuFeign.findById(id);
        model.addAttribute("menu",dto.getData());
        return "menu/view";
    }

    /**
     * 保存
     * @param dto
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> save(@RequestBody AdmMenuDto dto) {
        AdmUserDto currentUser=getCurrent();
        dto.setCreateBy(currentUser.getUserCode());
        return admMenuFeign.save(dto);
    }

    /**
     * 修改
     * @param dto
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> updateAdmUser(@RequestBody AdmMenuDto dto) {
        AdmUserDto currentUser=getCurrent();
        dto.setCreateBy(currentUser.getUserCode());
        ResponseDto<Boolean> responseDto=admMenuFeign.update(dto);
        if(responseDto.isSuccess()){
            return ResponseDtoFactory.toSuccess("更新成功",Boolean.TRUE);
        }else{
            return responseDto;
        }
    }

    /**
     * 列表
     * @return
     */
    @PostMapping(value = "/list")
    @ResponseBody
    public PageResultDto<List<MenuListDto>> list(@RequestBody BasePageDto searchDto){
        return admMenuFeign.findByPage(searchDto);
    }

    /**
     * 删除
     * @return
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public ResponseDto<Boolean> delete(@RequestBody List<Long> ids) {
        return admMenuFeign.deleteById(ids);
    }
}
