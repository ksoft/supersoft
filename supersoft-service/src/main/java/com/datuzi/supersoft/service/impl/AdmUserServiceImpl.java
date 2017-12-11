package com.datuzi.supersoft.service.impl;

import com.datuzi.supersoft.dto.*;
import com.datuzi.supersoft.service.AdmUserService;
import com.datuzi.supersoft.dao.AdmUserRepository;
import com.datuzi.supersoft.entity.AdmUser;
import com.datuzi.supersoft.utils.EntityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangjianbo
 * @date 2017/12/8
 */
@Service
public class AdmUserServiceImpl implements AdmUserService {
    @Autowired
    private AdmUserRepository admUserRepository;


    @Override
    public  ResponseDto<AdmUserDto> findAdmUser(LoginUserDto loginUserDto) {
        AdmUserDto admUserDto=new AdmUserDto();
        AdmUser user=admUserRepository.findAdmUserByUserCodeAndPassword(loginUserDto.getUserCode(),loginUserDto.getPassword());
        if(user!=null){
            BeanUtils.copyProperties(user,admUserDto);
        }
        return ResponseDtoFactory.toSuccess(admUserDto);
    }

    @Override
    public ResponseDto<Boolean> saveAdmUser(AdmUserDto admUserDto) {
        AdmUser admUser=EntityUtil.translate(admUserDto,AdmUser.class);
        admUserRepository.save(admUser);
        return ResponseDtoFactory.toSuccess(Boolean.TRUE);
    }
}
