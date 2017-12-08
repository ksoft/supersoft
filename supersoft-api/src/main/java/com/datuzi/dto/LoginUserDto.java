package com.datuzi.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangjianbo
 * @date 2017/12/7
 */
@Data
public class LoginUserDto implements Serializable{
    private String userCode;
    private String password;
}
