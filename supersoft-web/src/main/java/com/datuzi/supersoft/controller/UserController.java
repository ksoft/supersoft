package com.datuzi.supersoft.controller;

import com.datuzi.supersoft.controller.base.BaseController;
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
public class UserController extends BaseController {
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
    @PostMapping(value = "delete")
    @ResponseBody
    public ResponseDto<Boolean> delete(@RequestBody List<Long> ids) {
        AdmUserDto current=getCurrent();
        if(ids.contains(current.getId())){
            ids.remove(current.getId());
        }
        return admUserFeign.deleteAdmUserById(ids);
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

    /**
     * 新增
     * @param admUserDto
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> saveAdmUser(@RequestBody AdmUserDto admUserDto) {
        AdmUserDto currentUser=getCurrent();
        admUserDto.setCreateBy(currentUser.getUserCode());
        ResponseDto<Boolean> user=admUserFeign.saveAdmUser(admUserDto);
        return user;
    }

    /**
     * 修改
     * @param admUserDto
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> updateAdmUser(@RequestBody AdmUserDto admUserDto) {
        AdmUserDto currentUser=getCurrent();
        admUserDto.setCreateBy(currentUser.getUserCode());
        ResponseDto<Boolean> user=admUserFeign.updateAdmUser(admUserDto);
        return user;
    }

    /**
     * 个人信息
     * @return
     */
    @GetMapping(value = "myInfo")
    public String myInfo(Model model) {
        model.addAttribute("user",super.getCurrent());
        return "user/myInfo";
    }
}
