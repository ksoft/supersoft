package com.datuzi.supersoft.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangjianbo
 * @date 2018/1/4
 */
@Data
@AllArgsConstructor
public class LeftMenuSearchDto implements Serializable{
    private Long roleId;
    private Long pid;
}
