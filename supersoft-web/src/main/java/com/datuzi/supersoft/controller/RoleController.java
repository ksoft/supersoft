package com.datuzi.supersoft.controller;

import com.datuzi.supersoft.controller.base.BaseController;
import com.datuzi.supersoft.dto.*;
import com.datuzi.supersoft.feign.AdmMenuFeign;
import com.datuzi.supersoft.feign.AdmRoleFeign;
import com.datuzi.supersoft.feign.AdmRoleMenuFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangjianbo
 * @date 2017/12/28
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController{
    @Autowired
    private AdmRoleFeign admRoleFeign;
    @Autowired
    private AdmMenuFeign admMenuFeign;
    @Autowired
    private AdmRoleMenuFeign admRoleMenuFeign;

    /**
     * 列表页
     * @return
     */
    @GetMapping(value = "/index")
    public String index() {
        return "role/list";
    }

    /**
     * 新增
     * @return
     */
    @GetMapping(value = "/add")
    public String add() {
        return "role/add";
    }

    /**
     * 查看页
     * @return
     */
    @GetMapping(value = "/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        ResponseDto<AdmRoleDto> dto=admRoleFeign.findById(id);
        model.addAttribute("role",dto.getData());
        return "role/view";
    }

    /**
     * 编辑页
     * @return
     */
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        ResponseDto<AdmRoleDto> dto=admRoleFeign.findById(id);
        model.addAttribute("role",dto.getData());
        return "role/edit";
    }

    /**
     * 授权
     * @return
     */
    @GetMapping(value = "/auth/{id}")
    public String auth(@PathVariable Long id, Model model) {
        ResponseDto<AdmRoleDto> role=admRoleFeign.findById(id);
        model.addAttribute("role",role.getData());
        return "role/auth";
    }

    /**
     *获取整个菜单树
     * @return
     */
    @GetMapping(value = "/findMenuTree/{roleId}")
    @ResponseBody
    public List<MenuTreeDto> findMenuTree(@PathVariable Long roleId) {
        return admMenuFeign.findMenuTree(roleId).getData();
    }

    /**
     * 保存授权
     * @param dto
     * @return
     */
    @RequestMapping(value = "/saveAuth",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> saveAuth(@RequestBody AdmRoleMenuSaveDto dto) {
        AdmUserDto admUserDto=getCurrent();
        List<AdmRoleMenuDto> list=new ArrayList<>();
        for(Long menuId:dto.getMenuIdList()){
            AdmRoleMenuDto admRoleMenuDto=new AdmRoleMenuDto();
            admRoleMenuDto.setRoleId(dto.getRoleId());
            admRoleMenuDto.setMenuId(menuId);
            admRoleMenuDto.setCreateBy(admUserDto.getUserName());
            list.add(admRoleMenuDto);
        }
        return admRoleMenuFeign.save(list);
    }

    /**
     * 删除
     * @return
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public ResponseDto<Boolean> delete(@RequestBody List<Long> ids) {
        AdmUserDto current=getCurrent();
        if(ids.contains(current.getRoleId())){
            ids.remove(current.getRoleId());
            ids.remove(0L);
        }
        return admRoleFeign.deleteById(ids);
    }

    /**
     * 列表
     * @return
     */
    @PostMapping(value = "/list")
    @ResponseBody
    public PageResultDto<List<AdmRoleDto>> list(@RequestBody BasePageDto searchDto){
        return admRoleFeign.findByPage(searchDto);
    }

    /**
     * 新增
     * @param dto
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> save(@RequestBody AdmRoleDto dto) {
        AdmUserDto currentUser=getCurrent();
        dto.setCreateBy(currentUser.getUserCode());
        return admRoleFeign.save(dto);
    }

    /**
     * 修改
     * @param dto
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Boolean> updateAdmUser(@RequestBody AdmRoleDto dto) {
        AdmUserDto currentUser=getCurrent();
        dto.setCreateBy(currentUser.getUserCode());
        ResponseDto<Boolean> responseDto=admRoleFeign.update(dto);
        if(responseDto.isSuccess()){
            return ResponseDtoFactory.toSuccess("更新成功",Boolean.TRUE);
        }else{
            return ResponseDtoFactory.toError("更新失败");
        }
    }
}
