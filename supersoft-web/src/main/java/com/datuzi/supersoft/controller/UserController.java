package com.datuzi.supersoft.controller;

import com.datuzi.supersoft.dto.*;
import com.datuzi.supersoft.feign.AdmUserFeign;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * @author zhangjianbo
 * @date 2017/12/11
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AdmUserFeign admUserFeign;

    /**
     * 列表页
     * @return
     */
    @GetMapping(value = "index")
    public String main() {
        return "user/list";
    }

    /**
     * 新增
     * @return
     */
    @GetMapping(value = "add")
    public String add() {
        return "user/add";
    }

    /**
     * 查看页
     * @return
     */
    @GetMapping(value = "view/{id}")
    public String view(@PathVariable Long id, Model model) {
        ResponseDto<UserListDto> dto=admUserFeign.findAdmUserById(id);
        model.addAttribute("user",dto.getData());
        return "user/view";
    }

    /**
     * 编辑页
     * @return
     */
    @GetMapping(value = "edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        ResponseDto<UserListDto> dto=admUserFeign.findAdmUserById(id);
        model.addAttribute("user",dto.getData());
        return "user/edit";
    }

    /**
     * 查看页
     * @return
     */
    @GetMapping(value = "delete/{id}")
    @ResponseBody
    public ResponseDto<Boolean> delete(@PathVariable Long id) {
        return admUserFeign.deleteAdmUserById(id);
    }

    /**
     * 列表
     * @return
     */
    @PostMapping(value = "/list")
    @ResponseBody
    public PageResultDto<List<UserListDto>> list(@RequestBody UserSearchDto searchDto){
        return admUserFeign.findUserPage(searchDto);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> saveAdmUser(@RequestBody AdmUserDto admUserDto) {
        admUserDto.setCreateBy("Sys");
        admUserDto.setCreateDt(new Date());
        ResponseDto<Boolean> user=admUserFeign.saveAdmUser(admUserDto);
        return user;
    }
}
