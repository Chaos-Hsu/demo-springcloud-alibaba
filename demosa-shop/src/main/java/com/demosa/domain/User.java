package com.demosa.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/25 11:15 上午
 */
@Entity(name = "shop_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//数据库自增长
    private Integer uid;

    private String username;

    private String password;

    private String telephone;

}
