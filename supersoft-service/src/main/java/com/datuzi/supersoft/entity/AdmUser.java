package com.datuzi.supersoft.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 用户
 * @author zhangjianbo
 * @date 2017/12/8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ADM_USERS")
public class AdmUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userCode;
    private String password;
    private String userName;
    private String email;
    private String roleCode;
}
