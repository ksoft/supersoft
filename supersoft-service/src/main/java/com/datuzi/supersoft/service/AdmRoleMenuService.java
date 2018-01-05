package com.datuzi.supersoft.service;

import com.datuzi.supersoft.dto.AdmRoleMenuDto;
import com.datuzi.supersoft.dto.ResponseDto;

import java.util.List;

/**
 * @author zhangjianbo
 * @date 2018/1/5
 */
public interface AdmRoleMenuService {
    /**
     * 保存
     * @param roleMenuDtoList
     * @return
     */
    ResponseDto<Boolean> save(List<AdmRoleMenuDto> roleMenuDtoList);
}
