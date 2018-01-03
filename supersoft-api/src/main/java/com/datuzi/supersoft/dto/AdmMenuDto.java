package com.datuzi.supersoft.dto;

import com.datuzi.supersoft.enums.MenuType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangjianbo
 * @date 2018/1/3
 */
@Data
public class AdmMenuDto implements Serializable {
    private Long id;
    private String title;
    private String icon;
    private String href;
    private Boolean spread;
    private Long pid;
    private Integer status;
    private MenuType type;
    private Date createDt;
    private String createBy;
}
