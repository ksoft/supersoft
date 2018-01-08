package com.datuzi.supersoft.service;

import com.datuzi.supersoft.dto.AdmLogDto;
import com.datuzi.supersoft.dto.BasePageDto;
import com.datuzi.supersoft.dto.PageResultDto;
import com.datuzi.supersoft.dto.ResponseDto;

import java.util.List;

/**
 * Created by 27度 on 2018/1/7 0007.
 */
public interface AdmLogService {
    /**
     * 列表分页
     * @param searchDto
     * @return
     */
    PageResultDto<List<AdmLogDto>> findByPage(final BasePageDto searchDto);

    /**
     * 保存
     * @param dto
     * @return
     */
    ResponseDto<Boolean> save(AdmLogDto dto);

    /**
     * 删除
     * @param ids
     * @return
     */
    ResponseDto<Boolean> deleteById(List<Long> ids);
}
