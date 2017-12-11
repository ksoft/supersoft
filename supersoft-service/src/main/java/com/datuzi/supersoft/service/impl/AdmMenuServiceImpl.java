package com.datuzi.supersoft.service.impl;

import com.datuzi.dto.*;
import com.datuzi.service.AdmMenuService;
import com.datuzi.supersoft.dao.AdmMenuRepository;
import com.datuzi.supersoft.entity.AdmMenu;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 27度 on 2017/12/9 0009.
 */
@Service
public class AdmMenuServiceImpl implements AdmMenuService{
    @Autowired
    AdmMenuRepository admMenuRepository;

    @Override
    public ResponseDto<List<TopMenuDto>> topMenu(Long roleId) {
        List<TopMenuDto> topMenuDtoList=new ArrayList<>();
        List<AdmMenu> menuList=admMenuRepository.findTopMenu(roleId);
        for(AdmMenu admMenu:menuList){
            TopMenuDto topMenuDto=new TopMenuDto();
            BeanUtils.copyProperties(admMenu,topMenuDto);
            topMenuDtoList.add(topMenuDto);
        }
        return ResponseDtoFactory.toSuccess(topMenuDtoList);
    }

    @Override
    public ResponseDto<List<LeftMenuDto>> leftMenu(Long pid) {
        List<LeftMenuDto> leftMenuDtoList=new ArrayList<>();
        List<AdmMenu> menuList=admMenuRepository.findLeftMenu(pid);
        for(AdmMenu admMenu:menuList){
            if(admMenu.getPid().equals(pid)) {
                LeftMenuDto current = new LeftMenuDto();
                BeanUtils.copyProperties(admMenu,current);
                leftMenuDtoList.add(getChildMenu(current,menuList));
            }
        }
        return ResponseDtoFactory.toSuccess(leftMenuDtoList);
    }

    private LeftMenuDto getChildMenu(LeftMenuDto current,List<AdmMenu> menuList){
        for(AdmMenu subMenu:menuList){
            if(subMenu.getPid().equals(current.getId())){
                LeftMenuDto subMenuDto=new LeftMenuDto();
                BeanUtils.copyProperties(subMenu,subMenuDto);
                current.getChildren().add(subMenuDto);
                getChildMenu(subMenuDto,menuList);
            }
        }
        return current;
    }
}
