package com.datuzi.supersoft.service.impl;

import com.datuzi.dto.ResponseDto;
import com.datuzi.dto.ResponseDtoFactory;
import com.datuzi.dto.TopMenuDto;
import com.datuzi.service.AdmMenuService;
import com.datuzi.supersoft.dao.AdmMenuRepository;
import com.datuzi.supersoft.entity.AdmMenu;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 27度 on 2017/12/9 0009.
 */
@Service
public class AdmMenuServiceImpl implements AdmMenuService{
    @Autowired
    AdmMenuRepository admMenuRepository;

    @Override
    public ResponseDto<List<TopMenuDto>> topMenu(Long roleCode) {
        List<TopMenuDto> topMenuDtoList=new ArrayList<>();
        List<AdmMenu> menuList=admMenuRepository.findTopMenuByRoleCode(roleCode);
        for(AdmMenu admMenu:menuList){
            TopMenuDto topMenuDto=new TopMenuDto();
            BeanUtils.copyProperties(admMenu,topMenuDto);
            topMenuDtoList.add(topMenuDto);
        }
        return ResponseDtoFactory.toSuccess(topMenuDtoList);
    }

}
