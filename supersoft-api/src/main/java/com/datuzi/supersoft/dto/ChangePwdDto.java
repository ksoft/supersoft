package com.datuzi.supersoft.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangjianbo
 * @date 2018/1/5
 */
@Data
public class ChangePwdDto implements Serializable{
    private Long id;
    private String oldPwd;
    private String newPwd;
    private String newConfirmPwd;
}
