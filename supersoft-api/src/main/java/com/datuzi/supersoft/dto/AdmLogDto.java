package com.datuzi.supersoft.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 27度 on 2018/1/7 0007.
 */
@Data
public class AdmLogDto implements Serializable{
    private Long id;
    private Long userId;
    private String userName;
    private String ip;
    private Date createDt;
    private String createBy;
}
