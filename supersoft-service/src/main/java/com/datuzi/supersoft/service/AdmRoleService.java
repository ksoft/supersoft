package com.datuzi.supersoft.service;

import com.datuzi.supersoft.dto.*;
import com.datuzi.supersoft.entity.AdmRole;

import java.util.List;

/**
 * @author zhangjianbo
 * @date 2017/12/11
 */
public interface AdmRoleService {
    /**
     * 查找
     * @param id
     * @return
     */
    ResponseDto<RoleListDto> findById(Long id);

    /**
     * 获取全部菜单
     * @return
     */
    ResponseDto<List<RoleListDto>> findAll();

    /**
     * 保存
     * @param dto
     * @return
     */
    ResponseDto<Boolean> save(AdmRoleDto dto);

    /**
     * 更新
     * @param dto
     * @return
     */
    ResponseDto<Boolean> update(AdmRoleDto dto);

    /**
     * 列表分页
     * @param searchDto
     * @return
     */
    PageResultDto<List<RoleListDto>> findByPage(BasePageDto searchDto);

    /**
     * 删除
     * @param ids
     * @return
     */
    ResponseDto<Boolean> deleteById(List<Long> ids);
}
