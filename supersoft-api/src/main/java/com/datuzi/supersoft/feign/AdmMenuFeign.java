package com.datuzi.supersoft.feign;

import com.datuzi.supersoft.constant.Constants;
import com.datuzi.supersoft.dto.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 27度 on 2017/12/9 0009.
 */
@FeignClient(name= Constants.SERVICE_PROVIDER)
public interface AdmMenuFeign {
    @RequestMapping(value = "/menu/topMenu",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<List<TopMenuDto>> topMenu(@RequestBody Long roleId);

    @RequestMapping(value = "/menu/leftMenu",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<List<LeftMenuDto>> leftMenu(@RequestBody LeftMenuSearchDto searchDto);

    /**
     * 查找
     * @param searchDto
     * @return
     */
    @RequestMapping(value = "/menu/findByPage",method = RequestMethod.POST)
    @ResponseBody
    PageResultDto<List<AdmMenuDto>> findByPage(@RequestBody BasePageDto searchDto);

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/menu/deleteById",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<Boolean> deleteById(List<Long> id);

    /**
     * 查找全部
     * @return
     */
    @RequestMapping(value = "/menu/findAll",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<List<AdmMenuDto>> findAll();

    /**
     * 查找
     * @return
     */
    @RequestMapping(value = "/menu/findMenuTree",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<List<MenuTreeDto>> findMenuTree(@RequestBody Long roleId);

    /**
     * 查找
     * @param id
     * @return
     */
    @RequestMapping(value = "/menu/findById/{id}",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<AdmMenuDto> findById(@PathVariable("id") Long id);

    /**
     * 保存
     * @param dto
     * @return
     */
    @RequestMapping(value = "/menu/save",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<Boolean> save(@RequestBody AdmMenuDto dto);

    /**
     * 更新
     * @param dto
     * @return
     */
    @RequestMapping(value = "/menu/update",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<Boolean> update(@RequestBody AdmMenuDto dto);
}
