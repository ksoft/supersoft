package com.datuzi.service;

/**
 * @author zhangjianbo
 * @date 2017/12/8
 */
public interface AdmUserService {
    /**
     * 查找用户
     * @param userCode
     * @param password
     * @return
     */
    Boolean findAdmUserByUserCodeAndPassword(String userCode,String password);
}
