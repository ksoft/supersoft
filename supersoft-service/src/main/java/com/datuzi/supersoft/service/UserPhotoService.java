package com.datuzi.supersoft.service;

import com.datuzi.supersoft.dto.BasePageDto;
import com.datuzi.supersoft.dto.PageResultDto;
import com.datuzi.supersoft.dto.ResponseDto;
import com.datuzi.supersoft.dto.UserPhotoDto;

import java.util.List;

/**
 * @author zhangjianbo
 * @date 2018/1/8
 */
public interface UserPhotoService {
    /**
     * 查找
     * @param id
     * @return
     */
    ResponseDto<UserPhotoDto> findById(Long id);

    /**
     * 获取全部菜单
     * @return
     */
    ResponseDto<List<UserPhotoDto>> findAll();

    /**
     * 保存
     * @param dto
     * @return
     */
    ResponseDto<Boolean> save(UserPhotoDto dto);

    /**
     * 更新
     * @param dto
     * @return
     */
    ResponseDto<Boolean> update(UserPhotoDto dto);

    /**
     * 列表分页
     * @param searchDto
     * @return
     */
    PageResultDto<List<UserPhotoDto>> findByPage(final BasePageDto searchDto);

    /**
     * 删除
     * @param ids
     * @return
     */
    ResponseDto<Boolean> deleteById(List<Long> ids);
}
