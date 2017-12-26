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
     * 查找用户
     * @param id
     * @return
     */
    ResponseDto<UserListDto> findAdmUserById(Long id);

    /**
     * 保存
     * @param admUserDto
     * @return
     */
    ResponseDto<Boolean> saveAdmUser(AdmUserDto admUserDto);

    /**
     * 更新
     * @param admUserDto
     * @return
     */
    ResponseDto<Boolean> updateAdmUser(AdmUserDto admUserDto);

    /**
     * 列表分页
     * @param searchDto
     * @return
     */
    PageResultDto<List<UserListDto>> findUserPage(UserSearchDto searchDto);

    /**
     * 删除用户
     * @param ids
     * @return
     */
    ResponseDto<Boolean> deleteAdmUserById(List<Long> ids);
}
