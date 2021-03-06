package com.datuzi.supersoft.controller;

import com.datuzi.supersoft.controller.base.BaseController;
import com.datuzi.supersoft.dto.*;
import com.datuzi.supersoft.feign.AdmRoleFeign;
import com.datuzi.supersoft.feign.AdmUserFeign;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private AdmRoleFeign admRoleFeign;


    /**
     * 列表页
     * @return
     */
    @GetMapping(value = "/index")
    public String index() {
        return "user/list";
    }

    /**
     * 新增
     * @return
     */
    @GetMapping(value = "/add")
    public String add(Model model) {
        ResponseDto<List<AdmRoleDto>> responseDto=admRoleFeign.findAll();
        model.addAttribute("roles",responseDto.getData());
        return "user/add";
    }

    /**
     * 查看页
     * @return
     */
    @GetMapping(value = "/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        ResponseDto<AdmUserDto> dto=admUserFeign.findById(id);
        model.addAttribute("user",dto.getData());
        ResponseDto<List<AdmRoleDto>> responseDto=admRoleFeign.findAll();
        model.addAttribute("roles",responseDto.getData());
        return "user/view";
    }

    /**
     * 编辑页
     * @return
     */
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        ResponseDto<AdmUserDto> dto=admUserFeign.findById(id);
        model.addAttribute("user",dto.getData());
        ResponseDto<List<AdmRoleDto>> responseDto=admRoleFeign.findAll();
        model.addAttribute("roles",responseDto.getData());
        return "user/edit";
    }

    /**
     * 修改密码页
     * @return
     */
    @GetMapping(value = "/changePwd")
    public String changePwd(Model model) {
        model.addAttribute("user",getCurrent());
        return "user/changePwd";
    }

    /**
     * 删除
     * @return
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public ResponseDto<Boolean> delete(@RequestBody List<Long> ids) {
        AdmUserDto current=getCurrent();
        if(ids.contains(current.getId())){
            ids.remove(current.getId());
        }
        return admUserFeign.deleteById(ids);
    }

    /**
     * 列表
     * @return
     */
    @PostMapping(value = "/list")
    @ResponseBody
    public PageResultDto<List<AdmUserDto>> list(@RequestBody BasePageDto searchDto){
        return admUserFeign.findByPage(searchDto);
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
        return admUserFeign.save(admUserDto);
    }

    /**
     * 修改
     * @param admUserDto
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> updateAdmUser(@RequestBody AdmUserDto admUserDto, HttpServletRequest request) {
        AdmUserDto currentUser=getCurrent();
        admUserDto.setCreateBy(currentUser.getUserCode());
        ResponseDto<AdmUserDto> responseDto=admUserFeign.update(admUserDto);
        if(responseDto.isSuccess()){
            //如果更新的是当前用户，刷新用户缓存
            if(admUserDto.getId().equals(currentUser.getId())){
                super.refreshCurrent(request,responseDto.getData());
            }
            return ResponseDtoFactory.toSuccess("更新成功",Boolean.TRUE);
        }else{
            return ResponseDtoFactory.toError("更新失败",Boolean.FALSE);
        }
    }

    /**
     * 修改密码
     * @param changePwdDto
     * @return
     */
    @RequestMapping(value = "/updatePwd",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> updatePwd(@RequestBody ChangePwdDto changePwdDto, HttpServletRequest request) {
        AdmUserDto currentUser=getCurrent();
        if(!changePwdDto.getOldPwd().equals(currentUser.getPassword())){
            return ResponseDtoFactory.toError("旧密码不正确",Boolean.FALSE);
        }
        if(!changePwdDto.getNewPwd().equals(changePwdDto.getNewConfirmPwd())){
            return ResponseDtoFactory.toError("两次新密码不一样",Boolean.FALSE);
        }
        currentUser.setPassword(changePwdDto.getNewPwd());
        ResponseDto<AdmUserDto> responseDto=admUserFeign.update(currentUser);
        if(responseDto.isSuccess()){
            //刷新用户缓存
            super.refreshCurrent(request,responseDto.getData());
            return ResponseDtoFactory.toSuccess("更新成功",Boolean.TRUE);
        }else{
            return ResponseDtoFactory.toError("更新失败",Boolean.FALSE);
        }
    }

    /**
     * 个人信息
     * @return
     */
    @GetMapping(value = "/myInfo")
    public String myInfo(HttpServletRequest request,Model model) {
        ResponseDto<AdmUserDto> responseDto=admUserFeign.findById(super.getCurrent().getId());
        AdmUserDto userListDto=responseDto.getData();
        AdmUserDto admUserDto=new AdmUserDto();
        BeanUtils.copyProperties(userListDto,admUserDto);
        super.refreshCurrent(request,admUserDto);
        model.addAttribute("user",admUserDto);
        ResponseDto<List<AdmRoleDto>> rolesRespon=admRoleFeign.findAll();
        model.addAttribute("roles",rolesRespon.getData());
        return "user/myInfo";
    }
}
