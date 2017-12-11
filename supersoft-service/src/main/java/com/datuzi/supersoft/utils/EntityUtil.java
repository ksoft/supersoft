package com.datuzi.supersoft.utils;

import com.datuzi.supersoft.dto.AdmUserDto;
import com.datuzi.supersoft.entity.AdmUser;
import org.springframework.beans.BeanUtils;

/**
 * @author zhangjianbo
 * @date 2017/12/11
 */
public class EntityUtil{
    public static<E> E  translate(Object dto,Class<E> clazz){
        E entity= null;
        try {
            entity = clazz.newInstance();
            BeanUtils.copyProperties(dto,entity);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return entity;
    }
}
