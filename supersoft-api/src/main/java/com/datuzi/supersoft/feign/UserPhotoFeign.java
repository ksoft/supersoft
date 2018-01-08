package com.datuzi.supersoft.feign;

import com.datuzi.supersoft.constant.Constants;
import com.datuzi.supersoft.dto.BasePageDto;
import com.datuzi.supersoft.dto.PageResultDto;
import com.datuzi.supersoft.dto.ResponseDto;
import com.datuzi.supersoft.dto.UserPhotoDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangjianbo
 * @date 2018/1/8
 */
@FeignClient(name= Constants.SERVICE_PROVIDER)
@RequestMapping(value = "/photo")
public interface UserPhotoFeign {
    /**
     * 保存
     * @param dto
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<Boolean> save(@RequestBody UserPhotoDto dto);

    /**
     * 更新
     * @param dto
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<Boolean> update(@RequestBody UserPhotoDto dto);

    /**
     * 查找
     * @param id
     * @return
     */
    @RequestMapping(value = "/findById/{id}",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<UserPhotoDto> findById(@PathVariable("id") Long id);

    /**
     * 查找全部
     * @return
     */
    @RequestMapping(value = "/findAll",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<List<UserPhotoDto>> findAll();

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteById",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<Boolean> deleteById(List<Long> id);


    /**
     * 分页查找
     * @param searchDto
     * @return
     */
    @RequestMapping(value = "/findByPage",method = RequestMethod.POST)
    @ResponseBody
    PageResultDto<List<UserPhotoDto>> findByPage(@RequestBody BasePageDto searchDto);
}
