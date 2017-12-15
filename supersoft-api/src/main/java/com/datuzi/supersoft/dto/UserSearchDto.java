package com.datuzi.supersoft.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by 27度 on 2017/12/16 0016.
 */
@Data
public class UserSearchDto extends BasePageDto{
    private String userName;
    private String roleCode;
    private String email;
}
