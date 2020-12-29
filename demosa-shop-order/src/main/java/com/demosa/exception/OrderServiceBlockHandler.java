package com.demosa.exception;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/29 9:26 下午
 */

@Slf4j
public class OrderServiceBlockHandler {

    /**
     * 方法必须static修饰
     * @param e
     */
    public static void blockHandler2(BlockException e) {
        log.info("触发了blockhandlerclass,内容为:{}", e);
    }


}
