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
public interface AdmUserFeign {
    /**
     * 查找
     * @param loginUserDto
     * @return
     */
    @RequestMapping(value = "/user/findOne",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<AdmUserDto> findOne(@RequestBody LoginUserDto loginUserDto);

    /**
     * 保存
     * @param admUserDto
     * @return
     */
    @RequestMapping(value = "/user/save",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<Boolean> save(@RequestBody AdmUserDto admUserDto);

    /**
     * 更新
     * @param admUserDto
     * @return
     */
    @RequestMapping(value = "/user/update",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<AdmUserDto> update(@RequestBody AdmUserDto admUserDto);

    /**
     * 查找
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/findById/{id}",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<UserListDto> findById(@PathVariable("id") Long id);

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/deleteById",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<Boolean> deleteById(List<Long> id);


    /**
     * 查找
     * @param searchDto
     * @return
     */
    @RequestMapping(value = "/user/findByPage",method = RequestMethod.POST)
    @ResponseBody
    PageResultDto<List<UserListDto>> findByPage(@RequestBody BasePageDto searchDto);
}
