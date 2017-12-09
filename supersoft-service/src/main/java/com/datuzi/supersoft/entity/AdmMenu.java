package com.datuzi.supersoft.entity;

import com.datuzi.enums.MenuType;
import com.datuzi.enums.Status;
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
public class AdmMenu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String icon;
    private Boolean spread;
    private Long pid;
    private Long roleCode;
    private Status status;
    private MenuType type;

}
