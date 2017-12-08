package com.datuzi.supersoft.service.impl;

import com.datuzi.service.AdmUserService;
import com.datuzi.supersoft.dao.AdmUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangjianbo
 * @date 2017/12/8
 */
@Service
public class AdmUserServiceImpl implements AdmUserService{
    @Autowired
    private AdmUserRepository admUserRepository;


}
