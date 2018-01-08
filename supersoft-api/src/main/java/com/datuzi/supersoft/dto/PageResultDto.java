package com.datuzi.supersoft.dto;

import lombok.Data;

import java.io.Serializable;


/**
 * Created by 27度 on 2017/12/16 0016.
 */
@Data
public class PageResultDto<T> implements Serializable{
    private int code=0;
    private String msg="";
    private long count=0;
    private int totalPages=0;
    private T data;
}
