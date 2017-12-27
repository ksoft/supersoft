package com.datuzi.supersoft.entity;

import com.datuzi.supersoft.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * @author zhangjianbo
 * @date 2017/12/8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AdmUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userCode;
    private String password;
    private String userName;
    private String email;
    private String status;
    private String mobilePhone;
    private String sex;
    private RoleType roleCode;
    private Date createDt;
    private String createBy;
}
