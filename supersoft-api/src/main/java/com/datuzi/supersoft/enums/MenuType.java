package com.datuzi.supersoft.enums;

/**
 * Created by 27度 on 2017/12/9 0009.
 */
public enum  MenuType {
    TOP(0,"顶部菜单"),
    LEFT(1,"左侧菜单");

    private Integer key;
    private String value;

    MenuType(Integer key,String value){
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
