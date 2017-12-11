package com.datuzi.supersoft.service;

import com.datuzi.supersoft.dto.LeftMenuDto;
import com.datuzi.supersoft.dto.ResponseDto;
import com.datuzi.supersoft.dto.TopMenuDto;

import java.util.List;

/**
 * @author zhangjianbo
 * @date 2017/12/11
 */
public interface AdmMenuService {
    ResponseDto<List<TopMenuDto>> topMenu(Long roleId);

    ResponseDto<List<LeftMenuDto>> leftMenu(Long pid);
}
