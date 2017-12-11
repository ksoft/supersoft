package com.datuzi.supersoft.service;

import com.datuzi.supersoft.dto.AdmUserDto;
import com.datuzi.supersoft.dto.LoginUserDto;
import com.datuzi.supersoft.dto.ResponseDto;

/**
 * @author zhangjianbo
 * @date 2017/12/11
 */
public interface AdmUserService {
    ResponseDto<AdmUserDto> findAdmUser(LoginUserDto loginUserDto);

    ResponseDto<Boolean> saveAdmUser(AdmUserDto admUserDto);
}
