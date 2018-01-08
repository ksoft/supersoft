package com.datuzi.supersoft.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangjianbo
 * @date 2018/1/8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserPhoto implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String imgTitle;
    private String imgSrc;
    private Date createDt;
    private String createBy;
}
