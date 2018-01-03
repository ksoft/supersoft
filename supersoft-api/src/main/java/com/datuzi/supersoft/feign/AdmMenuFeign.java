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
    ResponseDto<List<LeftMenuDto>> leftMenu(@RequestBody Long pid);

    /**
     * 查找
     * @param searchDto
     * @return
     */
    @RequestMapping(value = "/menu/findByPage",method = RequestMethod.POST)
    @ResponseBody
    PageResultDto<List<MenuListDto>> findByPage(@RequestBody BasePageDto searchDto);

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/menu/deleteById",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<Boolean> deleteById(List<Long> id);
}
