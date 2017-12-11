package com.datuzi.supersoft.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangjianbo
 * @date 2017/12/11
 */
@Data
public class AdmUserDto implements Serializable {
    private String userCode;
    private String password;
    private String userName;
    private String email;
    private String roleCode;
}
