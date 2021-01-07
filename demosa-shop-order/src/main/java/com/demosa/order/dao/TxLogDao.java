package com.demosa.order.dao;

import com.demosa.domain.TxLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/25 4:36 下午
 */
public interface TxLogDao extends JpaRepository<TxLog, String> {


}
