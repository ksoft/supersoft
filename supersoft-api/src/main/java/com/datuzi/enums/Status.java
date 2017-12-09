package com.datuzi.enums;

/**
 * Created by 27度 on 2017/12/9 0009.
 */
public enum Status {
    INIT(0,"初始"),
    VALID(1,"有效"),
    INVALID(2,"无效 ");

    private Integer key;
    private String value;

    Status(Integer key,String value){
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
