package com.datuzi.supersoft.feign;

import com.datuzi.supersoft.constant.Constants;
import com.datuzi.supersoft.dto.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zhangjianbo
 * @date 2017/12/8
 */
@FeignClient(name= Constants.SERVICE_PROVIDER)
public interface AdmUserFeign {
    /**
     * 查找用户
     * @param loginUserDto
     * @return
     */
    @RequestMapping(value = "/user/findAdmUser",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<AdmUserDto> findAdmUser(@RequestBody LoginUserDto loginUserDto);


    /**
     * 查找用户
     * @param searchDto
     * @return
     */
    @RequestMapping(value = "/user/findUserPage",method = RequestMethod.POST)
    @ResponseBody
    PageResultDto<List<UserListDto>> findUserPage(@RequestBody UserSearchDto searchDto);
}
