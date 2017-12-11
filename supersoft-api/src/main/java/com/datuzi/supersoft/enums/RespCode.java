package com.datuzi.supersoft.enums;

public enum RespCode {

    ACK(0),//成功
    NACK(1),//失败
    UNAUTHORIZED(5);//未登陆

    //名称
    public Integer name;

    RespCode(Integer name) {
        this.name = name;
    }

    public Integer getName() {
        return name;
    }
}
