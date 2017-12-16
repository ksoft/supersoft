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
     * 查找用户
     * @param loginUserDto
     * @return
     */
    @RequestMapping(value = "/user/findAdmUser",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<AdmUserDto> findAdmUser(@RequestBody LoginUserDto loginUserDto);

    /**
     * 保存用户
     * @param admUserDto
     * @return
     */
    @RequestMapping(value = "/user/saveAdmUser",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<Boolean> saveAdmUser(@RequestBody AdmUserDto admUserDto);

    /**
     * 查找用户
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/findAdmUserById/{id}",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<UserListDto> findAdmUserById(@PathVariable("id") Long id);

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/deleteAdmUserById/{id}",method = RequestMethod.POST)
    @ResponseBody
    ResponseDto<Boolean> deleteAdmUserById(@PathVariable("id") Long id);


    /**
     * 查找用户
     * @param searchDto
     * @return
     */
    @RequestMapping(value = "/user/findUserPage",method = RequestMethod.POST)
    @ResponseBody
    PageResultDto<List<UserListDto>> findUserPage(@RequestBody UserSearchDto searchDto);
}
