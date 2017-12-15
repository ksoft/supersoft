package com.datuzi.supersoft.dao;

import com.datuzi.supersoft.dto.UserSearchDto;
import com.datuzi.supersoft.entity.AdmUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhangjianbo
 * @date 2017/12/8
 */
@Repository
public interface AdmUserRepository extends JpaSpecificationExecutor<AdmUser>,JpaRepository<AdmUser, Long> {
    /**
     * 查找用户
     * @param userCode
     * @param password
     * @return
     */
    AdmUser findAdmUserByUserCodeAndPassword(String userCode,String password);
}
