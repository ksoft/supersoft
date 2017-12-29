package com.datuzi.supersoft.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangjianbo
 * @date 2017/12/28
 */
@Data
public class AdmRoleDto implements Serializable{
    private Long id;
    private String name;
    private Integer status;
    private Date createDt;
    private String createBy;
}
