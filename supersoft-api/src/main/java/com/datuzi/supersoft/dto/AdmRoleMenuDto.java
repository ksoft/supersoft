package com.datuzi.supersoft.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangjianbo
 * @date 2018/1/5
 */
@Data
public class AdmRoleMenuDto implements Serializable{
    private Long id;
    private Long roleId;
    private Long menuId;
    private Date createDt;
    private String createBy;
}
