package com.datuzi.supersoft.feign;

import com.datuzi.supersoft.constant.Constants;
import com.datuzi.supersoft.dto.AdmRoleMenuDto;
import com.datuzi.supersoft.dto.ResponseDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zhangjianbo
 * @date 2018/1/5
 */
@FeignClient(name= Constants.SERVICE_PROVIDER)
public interface AdmRoleMenuFeign {
    /**
     * 保存
     * @param roleMenuDtoList
     * @return
     */
    @RequestMapping(value = "/roleMenu/save",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<Boolean> save(@RequestBody List<AdmRoleMenuDto> roleMenuDtoList);
}
