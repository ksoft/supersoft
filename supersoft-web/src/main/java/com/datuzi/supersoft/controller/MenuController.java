package com.datuzi.supersoft.controller;

import com.datuzi.dto.*;
import com.datuzi.service.AdmMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 27度 on 2017/12/9 0009.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private AdmMenuService admMenuService;

    /**
     * 本顶部菜单
     * @return
     */
    @PostMapping(value = "/topMenu")
    @ResponseBody
    public ResponseDto<List<TopMenuDto>> topMenu(){
        ResponseDto<List<TopMenuDto>> responseDto=admMenuService.topMenu(1L);
        return ResponseDtoFactory.toSuccess(responseDto.getData());
    }

    /**
     * 根据顶部菜单，获取左侧菜单
     * @return
     */
    @PostMapping(value = "/leftMenuByTopMenu/{pid}")
    @ResponseBody
    public ResponseDto<List<LeftMenuDto>> leftMenuByTopMenu(@PathVariable("pid") Integer pid){
        List<LeftMenuDto> list=new ArrayList<>();
        list.add(new LeftMenuDto(100L,"后台首页","larry-houtaishouye",Boolean.FALSE,"html/main.php"));

        List<LeftSubMenuDto> subMenuDtotList=new ArrayList<>();
        subMenuDtotList.add(new LeftSubMenuDto("个人信息","larry-gerenxinxi1","html/personInfo.html"));
        subMenuDtotList.add(new LeftSubMenuDto("修改密码","larry-xiugaimima2","html/changepwd.html"));
        subMenuDtotList.add(new LeftSubMenuDto("日志信息","larry-rizhi2","html/myloginfo.html"));
        list.add(new LeftMenuDto(101L,"我的面板","larry-gerenxinxi5",Boolean.TRUE,"",subMenuDtotList));

        List<LeftSubMenuDto> subMenuDtotList2=new ArrayList<>();
        subMenuDtotList2.add(new LeftSubMenuDto("用户列表","larry-yonghuliebiao1","html/main.html"));
        subMenuDtotList2.add(new LeftSubMenuDto("角色列表","larry-jiaoseguanli1","html/temp.html"));
        subMenuDtotList2.add(new LeftSubMenuDto("菜单管理","larry-caidanguanli","html/temp.html"));
        list.add(new LeftMenuDto(102L,"用户管理","larry-10103",Boolean.TRUE,"",subMenuDtotList2));

        return ResponseDtoFactory.toSuccess(list);
    }
}
