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

    @Query(value = "select a.* from adm_menu a inner join adm_role_menu b on a.id=b.menu_id inner join adm_role c on b.role_id=c.id where c.id=?1",nativeQuery = true)
    List<AdmMenu> findTopMenu(Long roleId);
}
