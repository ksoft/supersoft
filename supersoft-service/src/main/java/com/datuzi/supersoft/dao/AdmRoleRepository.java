package com.datuzi.supersoft.dao;

import com.datuzi.supersoft.entity.AdmRole;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhangjianbo
 * @date 2017/12/8
 */
@Repository
public interface AdmRoleRepository extends JpaSpecificationExecutor<AdmRole>,CrudRepository<AdmRole, Long>  {

}
