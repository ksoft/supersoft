package com.datuzi.supersoft.enums;

/**
 * Created by 27度 on 2017/12/9 0009.
 */
public enum RoleType {
    SYSTEM(0,"超级管理员"),
    ADMIN(1,"管理员");

    private Integer key;
    private String value;

    RoleType(Integer key, String value){
        this.key=key;
        this.value=value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
