package com.demosa.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 描述 : 消息事务记录
 * 作者 : 徐起超
 * 时间 : 2021/1/7 8:55 下午
 */
@Entity(name = "tx_log")
@Data
public class TxLog {

    @Id
    private String txId;

    private Date date;

}
