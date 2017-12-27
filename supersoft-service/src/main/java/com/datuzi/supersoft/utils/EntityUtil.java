package com.datuzi.supersoft.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static void copyPropertiesIgnoreNull(Object src, Object target){
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }
}
