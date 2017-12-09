package com.datuzi.supersoft.dao;

import com.datuzi.supersoft.entity.AdmMenu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 27度 on 2017/12/9 0009.
 */
@Repository
public interface AdmMenuRepository extends CrudRepository<AdmMenu,Long> {

    @Query(value = "select t from AdmMenu t where t.roleCode=?1")
    List<AdmMenu> findTopMenuByRoleCode(Long roleCode);
}
