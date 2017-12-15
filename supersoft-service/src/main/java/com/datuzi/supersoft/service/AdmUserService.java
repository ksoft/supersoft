package com.datuzi.supersoft.service;

import com.datuzi.supersoft.dto.*;

import java.util.List;

/**
 * @author zhangjianbo
 * @date 2017/12/11
 */
public interface AdmUserService {
    /**
     * 根据用户名密码查询
     * @param loginUserDto
     * @return
     */
    ResponseDto<AdmUserDto> findAdmUser(LoginUserDto loginUserDto);

    /**
     * 保存
     * @param admUserDto
     * @return
     */
    ResponseDto<Boolean> saveAdmUser(AdmUserDto admUserDto);

    /**
     * 列表分页
     * @param searchDto
     * @return
     */
    PageResultDto<List<UserListDto>> findUserPage(UserSearchDto searchDto);
}
