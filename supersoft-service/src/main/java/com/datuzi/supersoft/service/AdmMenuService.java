package com.datuzi.supersoft.service;

import com.datuzi.supersoft.dto.*;

import java.util.List;

/**
 * @author zhangjianbo
 * @date 2017/12/11
 */
public interface AdmMenuService {
    /**
     * 获取顶部菜单
     * @param roleId
     * @return
     */
    ResponseDto<List<TopMenuDto>> topMenu(Long roleId);

    /**
     * 获取左侧菜单
     * @param searchDto
     * @return
     */
    ResponseDto<List<LeftMenuDto>> leftMenu(LeftMenuSearchDto searchDto);

    /**
     * 查找
     * @return
     */
    ResponseDto<List<MenuTreeDto>> findMenuTree(Long roleId);

    /**
     * 列表分页
     * @param searchDto
     * @return
     */
    PageResultDto<List<AdmMenuDto>> findByPage(BasePageDto searchDto);

    /**
     * 查找
     * @param id
     * @return
     */
    ResponseDto<AdmMenuDto> findById(Long id);

    /**
     * 删除
     * @param ids
     * @return
     */
    ResponseDto<Boolean> deleteById(List<Long> ids);

    /**
     * 获取全部菜单
     * @return
     */
    ResponseDto<List<AdmMenuDto>> findAll();

    /**
     * 保存
     * @param dto
     * @return
     */
    ResponseDto<Boolean> save(AdmMenuDto dto);

    /**
     * 更新
     * @param dto
     * @return
     */
    ResponseDto<Boolean> update(AdmMenuDto dto);
}
