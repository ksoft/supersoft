package com.datuzi.supersoft.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangjianbo
 * @date 2017/12/11
 */
@Data
public class AdmUserDto implements Serializable {
    private Long id;
    private String userCode;
    private String password;
    private String userName;
    private String email;
    private Integer status;
    private String mobilePhone;
    private String sex;
    private String headIcon;
    private Long roleId;
    private String motto;
    private String roleName;
    private Date createDt;
    private String createBy;
}
