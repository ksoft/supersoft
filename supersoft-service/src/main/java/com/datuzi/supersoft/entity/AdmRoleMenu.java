package com.datuzi.supersoft.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author zhangjianbo
 * @date 2017/12/8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AdmRoleMenu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long roleId;
    private Long menuId;
}
