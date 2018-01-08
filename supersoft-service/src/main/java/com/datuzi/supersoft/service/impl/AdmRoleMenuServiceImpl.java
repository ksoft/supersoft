package com.datuzi.supersoft.service.impl;

import com.datuzi.supersoft.dao.AdmRoleMenuRepository;
import com.datuzi.supersoft.dto.AdmRoleMenuDto;
import com.datuzi.supersoft.dto.ResponseDto;
import com.datuzi.supersoft.dto.ResponseDtoFactory;
import com.datuzi.supersoft.entity.AdmRoleMenu;
import com.datuzi.supersoft.service.AdmRoleMenuService;
import com.datuzi.supersoft.utils.EntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhangjianbo
 * @date 2018/1/5
 */
@Service
public class AdmRoleMenuServiceImpl implements AdmRoleMenuService{
    @Autowired
    private AdmRoleMenuRepository admRoleMenuRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDto<Boolean> save(List<AdmRoleMenuDto> roleMenuDtoList) {
        Long roleId=roleMenuDtoList.get(0).getRoleId();
        admRoleMenuRepository.deleteByRoleId(roleId);
        for(AdmRoleMenuDto dto:roleMenuDtoList) {
            AdmRoleMenu entity=EntityUtil.translate(dto,AdmRoleMenu.class);
            admRoleMenuRepository.save(entity);
        }
        return ResponseDtoFactory.toSuccess("保存成功",Boolean.TRUE);
    }
}
