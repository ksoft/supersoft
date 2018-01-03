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
     * @param pid
     * @return
     */
    ResponseDto<List<LeftMenuDto>> leftMenu(Long pid);

    /**
     * 列表分页
     * @param searchDto
     * @return
     */
    PageResultDto<List<MenuListDto>> findByPage(BasePageDto searchDto);

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
    ResponseDto<List<MenuListDto>> findAll();

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
