package com.datuzi.supersoft.utils;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

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
            Field field=clazz.getDeclaredField("createDt");
            if(field!=null){
                field.setAccessible(true);
                Method fieldSetMet = clazz.getMethod("setCreateDt",field.getType());
                fieldSetMet.invoke(entity,new Date());
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return entity;
    }
}
