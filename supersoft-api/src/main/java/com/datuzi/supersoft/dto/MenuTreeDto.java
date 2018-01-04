package com.datuzi.supersoft.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangjianbo
 * @date 2018/1/4
 */
@Data
public class MenuTreeDto implements Serializable{
    private Long id;
    private Long pid;
    private String title;
    private Long value;
    private Boolean checked=false;
    private Boolean disabled=false;
    private List<MenuTreeDto> data=new ArrayList<>();
}
