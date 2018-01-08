package com.datuzi.supersoft.dao;

import com.datuzi.supersoft.entity.UserPhoto;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhangjianbo
 * @date 2018/1/8
 */
@Repository
public interface UserPhotoRepository extends JpaSpecificationExecutor<UserPhoto>,CrudRepository<UserPhoto, Long> {
}
