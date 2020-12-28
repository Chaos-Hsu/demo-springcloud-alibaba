package com.demosa.service;

import com.demosa.domain.Order;

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
}
