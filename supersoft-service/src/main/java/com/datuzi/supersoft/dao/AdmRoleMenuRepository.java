package com.datuzi.supersoft.dao;

import com.datuzi.supersoft.entity.AdmRoleMenu;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangjianbo
 * @date 2018/1/4
 */
@Repository
public interface AdmRoleMenuRepository extends JpaSpecificationExecutor<AdmRoleMenu>,CrudRepository<AdmRoleMenu, Long> {
    @Query(value = "select a.* from adm_role_menu a " +
            "where a.role_id=?1 ",nativeQuery = true)
    List<AdmRoleMenu> findMenuByRole(Long roleId);
}
