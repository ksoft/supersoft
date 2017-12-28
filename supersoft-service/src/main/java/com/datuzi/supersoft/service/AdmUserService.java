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
    ResponseDto<AdmUserDto> findOne(LoginUserDto loginUserDto);

    /**
     * 查找用户
     * @param id
     * @return
     */
    ResponseDto<UserListDto> findById(Long id);

    /**
     * 保存
     * @param admUserDto
     * @return
     */
    ResponseDto<Boolean> save(AdmUserDto admUserDto);

    /**
     * 更新
     * @param admUserDto
     * @return
     */
    ResponseDto<AdmUserDto> update(AdmUserDto admUserDto);

    /**
     * 列表分页
     * @param searchDto
     * @return
     */
    PageResultDto<List<UserListDto>> findByPage(BasePageDto searchDto);

    /**
     * 删除用户
     * @param ids
     * @return
     */
    ResponseDto<Boolean> deleteById(List<Long> ids);
}
