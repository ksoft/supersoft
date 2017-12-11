package com.datuzi.supersoft.dto;

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
    private Long id;
    private Long pid;
    private String title;
    private String icon;
    private Boolean spread;
    private String href;
    private List<LeftMenuDto> children=new ArrayList<>();
}
