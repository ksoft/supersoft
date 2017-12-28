package com.datuzi.supersoft.dto;


import lombok.Data;

import java.io.Serializable;

/**
 * Created by 27度 on 2017/12/16 0016.
 */
@Data
public class BasePageDto implements Serializable{
    private String queryParam;
    private int page=0;
    private int limit=20;
}
