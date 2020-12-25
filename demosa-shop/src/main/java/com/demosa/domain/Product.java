package com.demosa.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/25 11:21 上午
 */
@Entity(name = "shop_product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//数据库自增长
    private Integer pid;

    private String pname;

    private Double pprice;

    private Integer stocke;


}
