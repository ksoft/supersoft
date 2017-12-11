package com.datuzi.service;

import com.datuzi.constant.Constants;
import com.datuzi.dto.LeftMenuDto;
import com.datuzi.dto.ResponseDto;
import com.datuzi.dto.TopMenuDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 27度 on 2017/12/9 0009.
 */
@FeignClient(name= Constants.SERVICE_PROVIDER)
public interface AdmMenuService {
    @RequestMapping(value = "/topMenu",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<List<TopMenuDto>> topMenu(@RequestBody Long roleId);

    @RequestMapping(value = "/leftMenu",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<List<LeftMenuDto>> leftMenu(@RequestBody Long pid);
}
