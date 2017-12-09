package com.datuzi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by 27度 on 2017/12/9 0009.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeftSubMenuDto implements Serializable{
    private String title;
    private String icon;
    private String href;
}
