package com.demosa.order.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.demosa.domain.Order;
import com.demosa.domain.Product;
import com.demosa.domain.TxLog;
import com.demosa.order.api.ProductApi;
import com.demosa.order.dao.OrderDao;
import com.demosa.order.dao.TxLogDao;
import com.demosa.order.exception.OrderServiceBlockHandler;
import com.demosa.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;

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
    private TxLogDao txLogDao;

    @Autowired
    private RestTemplate restTemplate;

    //@Autowired
    //private DiscoveryClient discoveryClient;

    @Autowired
    private ProductApi productApi;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;


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

        if (product.getPid() == -10) {
            order.setOid(-100);
        } else {
            order.setUid(1);
            order.setUsername("xqc");
            order.setPid(pid);
            order.setPname(product.getPname());
            order.setPprice(product.getPprice());
            order.setNumber(1);
            orderDao.save(order);
        }
        //异步通知 1,TOPIC 2参数体
        rocketMQTemplate.convertAndSend("order-topic", order);
        return order;
    }

    @Override
    public void createTxOrder(Order order, String txId) throws Exception {
        //本地事务日志
        TxLog txLog = new TxLog();
        txLog.setTxId(txId);
        txLog.setDate(new Date());
        txLogDao.save(txLog);
        //int i = 1 / 0;
        //保存订单
        orderDao.save(order);
    }

    @Override
    @SentinelResource(
            value = "message"
            , blockHandlerClass = OrderServiceBlockHandler.class//该类中的blockHandel2
            , blockHandler = "blockHandler2"//资源发生BlockException异常进行捕获
            //, blockHandler = "blockHandler"//资源发生BlockException异常进行捕获
            , fallback = "fallback" //资源发生所有异常进行捕获
    )
    public void message() {
        /*i++;
        if (i % 3 == 0) {
            throw new RuntimeException("整除异常");
        }*/
    }

    @Override
    public void createOrderBefore(Integer pid) throws Exception {

        Product product = productApi.product(pid);
        if (product == null) {
            log.error("创建失败:{}产品不存在", pid);
            throw new RuntimeException("创建失败");
        }
        //创建订单
        Order order = new Order();
        if (product.getPid() == -10) {
            order.setOid(-100);
        } else {
            order.setUid(1);
            order.setUsername("xqc");
            order.setPid(pid);
            order.setPname(product.getPname());
            order.setPprice(product.getPprice());
            order.setNumber(1);
        }
        //事务ID
        String txId = UUID.randomUUID().toString();
        //给rocketMq服务 发送半事务消息
        rocketMQTemplate.sendMessageInTransaction(
                "tx_producer_group", //生产者组名
                "tx_topic",//主题
                MessageBuilder.withPayload(order).setHeader("txId", txId).build(),//消息内容
                order);
    }

    @Override
    @GlobalTransactional
    public Order createOrderBySeata(Integer pid) {
        //1.调用服务查询
        Product product = productApi.product(pid);
        if (product == null) {
            log.error("Seata创建失败:{}产品不存在", pid);
            return null;
        }
        //2.下单
        Order order = new Order();
        order.setUid(1);
        order.setUsername("xqc");
        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderDao.save(order);

        //3.扣库存
        productApi.deductStock(pid, 1);

        //4.异步通知 1,TOPIC 2参数体
        rocketMQTemplate.convertAndSend("order-topic", order);
        return order;
    }

    //int i = 0;


    public void blockHandler(BlockException e) {
        log.info("触发了blockhandler,内容为:{}", e);
    }

    public void fallback(Throwable e) {
        log.info("触发了fallback,内容为:{}", e);
    }
}
