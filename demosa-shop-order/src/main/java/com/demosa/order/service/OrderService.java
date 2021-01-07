package com.demosa.order.service;

import com.demosa.domain.Order;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/25 4:38 下午
 */
public interface OrderService {
    /**
     * 下单
     *
     * @param pid
     * @return
     */
    Order createOrder(Integer pid);

    /**
     * 事务下单
     *
     * @param order 订单内容
     * @param txId  事务ID
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    void createTxOrder(Order order, String txId) throws Exception;


    void message();

    /**
     * 发送半事务消息
     */
    void createOrderBefore(Integer pid) throws Exception;

}
