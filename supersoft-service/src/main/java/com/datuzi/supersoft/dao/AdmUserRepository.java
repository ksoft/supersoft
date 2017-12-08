package com.datuzi.supersoft.dao;

import com.datuzi.supersoft.entity.AdmUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhangjianbo
 * @date 2017/12/8
 */
@Repository
public interface AdmUserRepository extends CrudRepository<AdmUser,Long> {
    /**
     * 查找用户
     * @param userName
     * @param password
     * @return
     */
    AdmUser findAdmUserByUserNameAndPassword(String userName,String password);
}