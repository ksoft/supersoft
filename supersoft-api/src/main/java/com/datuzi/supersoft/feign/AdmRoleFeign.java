package com.datuzi.supersoft.feign;

import com.datuzi.supersoft.constant.Constants;
import com.datuzi.supersoft.dto.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangjianbo
 * @date 2017/12/28
 */
@FeignClient(name= Constants.SERVICE_PROVIDER)
public interface AdmRoleFeign {
    /**
     * 保存
     * @param dto
     * @return
     */
    @RequestMapping(value = "/role/save",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<Boolean> save(@RequestBody AdmRoleDto dto);

    /**
     * 更新
     * @param dto
     * @return
     */
    @RequestMapping(value = "/role/update",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<Boolean> update(@RequestBody AdmRoleDto dto);

    /**
     * 查找
     * @param id
     * @return
     */
    @RequestMapping(value = "/role/findById/{id}",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<RoleListDto> findById(@PathVariable("id") Long id);

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/role/deleteById",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<Boolean> deleteById(List<Long> id);


    /**
     * 分页查找
     * @param searchDto
     * @return
     */
    @RequestMapping(value = "/role/findByPage",method = RequestMethod.POST)
    @ResponseBody
    PageResultDto<List<RoleListDto>> findByPage(@RequestBody BasePageDto searchDto);
}
