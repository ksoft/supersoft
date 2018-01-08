package com.datuzi.supersoft.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangjianbo
 * @date 2018/1/8
 */
@Data
public class UserPhotoDto implements Serializable{
    private Long id;
    private Long userId;
    private String userName;
    private String imgTitle;
    private String imgSrc;
    private Date createDt;
    private String createBy;
}
