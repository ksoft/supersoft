package com.datuzi.supersoft.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangjianbo
 * @date 2018/1/5
 */
@Data
public class AdmRoleMenuSaveDto implements Serializable{
    private Long roleId;
    private List<Long> menuIdList=new ArrayList<>();
}
