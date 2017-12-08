package com.datuzi.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangjianbo
 * @date 2017/12/8
 */
public enum YesNo {
    YES(0,"是"),
    NO(1,"否");

    private Integer key;
    private String value;

    YesNo(Integer key,String value){
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
