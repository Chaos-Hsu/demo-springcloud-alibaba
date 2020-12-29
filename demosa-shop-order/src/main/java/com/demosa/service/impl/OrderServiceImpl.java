package com.demosa.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.demosa.api.ProductApi;
import com.demosa.dao.OrderDao;
import com.demosa.domain.Order;
import com.demosa.domain.Product;
import com.demosa.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/25 4:39 下午
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private RestTemplate restTemplate;

    //@Autowired
    //private DiscoveryClient discoveryClient;

    @Autowired
    private ProductApi productApi;


    @Override
    public Order createOrder(Integer pid) {
        //调用产品服务
        //Product product = restTemplate.getForObject("http://127.0.0.1:8072/product/" + pid, Product.class);
        //if (product == null) {
        //    log.error("创建失败:{}产品不存在", pid);
        //    return null;
        //}

        //List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        //ServiceInstance instance = instances.get(0);
        //Product product = restTemplate.getForObject("http://" + instance.getHost() + ":" + instance.getPort() + "/product/ " + pid, Product.class);
        //if (product == null) {
        //    log.error("创建失败:{}产品不存在", pid);
        //    return null;
        //}
        //List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        //int i = new Random().nextInt(instances.size());
        //ServiceInstance instance = instances.get(i);
        //Product product = restTemplate.getForObject("http://" + instance.getHost() + ":" + instance.getPort() + "/product/ " + pid, Product.class);
        //if (product == null) {
        //    log.error("创建失败:{}产品不存在", pid);
        //    return null;
        //}
        //Product product = restTemplate.getForObject("http://service-product/product/ " + pid, Product.class);
        Product product = productApi.product(pid);
        if (product == null) {
            log.error("创建失败:{}产品不存在", pid);
            return null;
        }
        Order order = new Order();
        order.setUid(1);
        order.setUsername("xqc");
        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderDao.save(order);
        return order;
    }

    //int i = 0;

    @Override
    @SentinelResource(
            value = "message"
            , blockHandler = "blockHandler"//资源发生BlockException异常进行捕获
            , fallback = "fallback" //资源发生所有异常进行捕获
    )
    public void message() {
        /*i++;
        if (i % 3 == 0) {
            throw new RuntimeException("整除异常");
        }*/
    }


    public void blockHandler(BlockException e) {
        log.info("触发了blockhandler,内容为:{}", e);
    }

    public void fallback(Throwable e) {
        log.info("触发了fallback,内容为:{}", e);
    }
}
