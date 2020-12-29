package com.demosa.controller;

import com.alibaba.fastjson.JSON;
import com.demosa.domain.Order;
import com.demosa.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/25 11:59 上午
 */
@Slf4j
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;


    @RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        log.info("进行下单{}号商品查询", pid);
        Order order = orderService.createOrder(pid);
        log.info("下单成功,内容为:{}", JSON.toJSON(order));
        return order;
    }

    @RequestMapping("/order2/prod/{pid}")
    public Order order2(@PathVariable("pid") Integer pid) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/order2/message")
    public String message() {
        return "高并发测试";
    }





    @RequestMapping("/order/sentinel/test1")
    public String sentinelTest1() {
        orderService.message();
        return "高并发测试1";
    }

    @RequestMapping("/order/sentinel/test2")
    public String sentinelTest2() {
        orderService.message();
        return "高并发测试2";
    }


}
