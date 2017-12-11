package com.datuzi.supersoft.feign;

import com.datuzi.supersoft.constant.Constants;
import com.datuzi.supersoft.dto.LeftMenuDto;
import com.datuzi.supersoft.dto.ResponseDto;
import com.datuzi.supersoft.dto.TopMenuDto;
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
}
