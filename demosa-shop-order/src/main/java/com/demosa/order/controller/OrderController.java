package com.demosa.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSON;
import com.demosa.domain.Order;
import com.demosa.order.service.OrderService;
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

    /**
     * 事务下单
     *
     * @param pid
     * @throws Exception
     */
    @RequestMapping("/tx/order/prod/{pid}")
    public void txOrder(@PathVariable("pid") Integer pid) throws Exception {
        log.info("进行事务下单{}号商品查询", pid);
        orderService.createOrderBefore(pid);
        log.info("事务下单成功");
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


    //int i = 0;

    /*@RequestMapping("/order/sentinel/test1")
    public String sentinelTest1() {
        i++;
        if (i % 3 == 0) {
            throw new RuntimeException("整除异常");
        }
        orderService.message();
        return "高并发测试1";
    }*/

    @RequestMapping("/order/sentinel/test2")
    public String sentinelTest2() {
        orderService.message();
        return "高并发测试2";
    }

    @RequestMapping("/order/sentinel/test3")
    @SentinelResource("test3")
    public String sentinelTest3(String name, Integer age) {
        log.info("sentinel热点规则测试,name:{},age:{}", name, age);
        return "高并发测试3";
    }

    @RequestMapping("/order/sentinel/test4")
    public String sentinelTest4() {
        orderService.message();
        return "高并发测试4";
    }
}
