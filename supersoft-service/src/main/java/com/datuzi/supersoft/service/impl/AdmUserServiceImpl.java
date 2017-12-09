package com.datuzi.supersoft.service.impl;

import com.datuzi.dto.LeftMenuDto;
import com.datuzi.dto.ResponseDto;
import com.datuzi.service.AdmUserService;
import com.datuzi.supersoft.dao.AdmUserRepository;
import com.datuzi.supersoft.entity.AdmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zhangjianbo
 * @date 2017/12/8
 */
@Service
public class AdmUserServiceImpl implements AdmUserService{
    @Autowired
    private AdmUserRepository admUserRepository;


    @Override
    public Boolean findAdmUserByUserCodeAndPassword(String userCode, String password) {
        AdmUser user=admUserRepository.findAdmUserByUserCodeAndPassword(userCode,password);
        if(user!=null){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
