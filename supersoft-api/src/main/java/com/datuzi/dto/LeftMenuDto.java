package com.datuzi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 27度 on 2017/12/9 0009.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeftMenuDto implements Serializable{
    private Long pid;
    private String title;
    private String icon;
    private Boolean spread;
    private String href;
    private List<LeftSubMenuDto> children=new ArrayList<>();

    public LeftMenuDto(Long pid,String title,String icon,Boolean spread,String href){
        this.pid=pid;
        this.title=title;
        this.icon=icon;
        this.spread=spread;
        this.href=href;
    }
}
