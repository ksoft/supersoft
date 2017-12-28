package com.datuzi.supersoft.dto;

import com.datuzi.supersoft.enums.Status;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangjianbo
 * @date 2017/12/28
 */
@Data
public class RoleListDto implements Serializable {
    private Long id;
    private String name;
    private Status status;
    private Date createDate;
    private String createBy;
}
