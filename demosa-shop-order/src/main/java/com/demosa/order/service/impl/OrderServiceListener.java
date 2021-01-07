package com.demosa.order.service.impl;

import com.demosa.domain.Order;
import com.demosa.domain.TxLog;
import com.demosa.order.dao.TxLogDao;
import com.demosa.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/**
 * 描述 : MQ监听器
 * 作者 : 徐起超
 * 时间 : 2020/12/25 4:39 下午
 */
@Service
@Slf4j
@RocketMQTransactionListener(txProducerGroup = "tx_producer_group")
public class OrderServiceListener implements RocketMQLocalTransactionListener {

    /**
     * 订单服务
     */
    @Autowired
    private OrderService orderService;

    @Autowired
    private TxLogDao txLogDao;

    /**
     * 执行本地事务
     *
     * @param message
     * @param o
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        try {
            //事务ID
            String txId = (String) message.getHeaders().get("txId");
            //本地事务
            Order order = (Order) o;
            orderService.createTxOrder(order, txId);
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            log.error("本地事务异常,提交MQ回滚:{}", e);
            //有异常回滚
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    /**
     * 消息回查
     *
     * @param message
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        //事务ID
        String txId = (String) message.getHeaders().get("txId");
        //数据库查询是否存在
        TxLog txLog = txLogDao.findById(txId).get();
        if (txLog != null) {
            //本地事务成功
            return RocketMQLocalTransactionState.COMMIT;
        }
        //异常
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}
