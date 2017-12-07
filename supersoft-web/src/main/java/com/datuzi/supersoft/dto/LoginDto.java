package com.datuzi.supersoft.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangjianbo
 * @date 2017/12/7
 */
@Data
public class LoginDto implements Serializable{
    private String userName;
    private String password;
    private String code;//验证码
}
