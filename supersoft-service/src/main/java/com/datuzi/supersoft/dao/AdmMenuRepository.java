package com.datuzi.supersoft.dao;

import com.datuzi.supersoft.entity.AdmMenu;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 27度 on 2017/12/9 0009.
 */
@Repository
public interface AdmMenuRepository extends JpaSpecificationExecutor<AdmMenu>,CrudRepository<AdmMenu, Long>  {

    @Query(value = "select a.* from adm_menu a " +
            "inner join adm_role_menu b on a.id=b.menu_id " +
            "inner join adm_role c on b.role_id=c.id " +
            "where c.id=?1 and a.type= 0 and a.status=0",nativeQuery = true)
    List<AdmMenu> findTopMenu(Long roleId);

    @Query(value = "select * from adm_menu where find_in_set (pid,getChild(?1))",nativeQuery = true)
    List<AdmMenu> findLeftMenu(Long pId);
}
