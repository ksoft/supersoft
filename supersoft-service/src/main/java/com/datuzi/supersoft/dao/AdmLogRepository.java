package com.datuzi.supersoft.dao;

import com.datuzi.supersoft.entity.AdmLog;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 27度 on 2018/1/7 0007.
 */
@Repository
public interface AdmLogRepository extends JpaSpecificationExecutor<AdmLog>,CrudRepository<AdmLog, Long> {
}
