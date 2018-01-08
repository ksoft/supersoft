package com.datuzi.supersoft.feign;

import com.datuzi.supersoft.constant.Constants;
import com.datuzi.supersoft.dto.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangjianbo
 * @date 2017/12/8
 */
@FeignClient(name= Constants.SERVICE_PROVIDER)
public interface AdmLogFeign {
    /**
     * 保存
     * @param dto
     * @return
     */
    @RequestMapping(value = "/log/save",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<Boolean> save(@RequestBody AdmLogDto dto);

    /**
     * 查找
     * @param id
     * @return
     */
    @RequestMapping(value = "/log/findById/{id}",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<AdmUserDto> findById(@PathVariable("id") Long id);

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/log/deleteById",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<Boolean> deleteById(List<Long> id);


    /**
     * 查找
     * @param searchDto
     * @return
     */
    @RequestMapping(value = "/log/findByPage",method = RequestMethod.POST)
    @ResponseBody
    PageResultDto<List<AdmLogDto>> findByPage(@RequestBody BasePageDto searchDto);
}
