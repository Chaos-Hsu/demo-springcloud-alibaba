package com.demosa.order.api;

import com.demosa.apis.BaseProductApi;
import com.demosa.order.api.fallback.ProdcutFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 描述 : 服务接口
 * 作者 : 徐起超
 * 时间 : 2020/12/28 2:27 下午
 */
@FeignClient(
        value = "service-product",
        path = "product"
        //fallback = ProdcutFallBack.class
        //fallbackFactory = ProdcutFallBackFactory.class
)
public interface ProductApi extends BaseProductApi {


    /**
     * 扣减库存
     *
     * @param pid 产品ID
     * @param num 扣减数量
     */
    @RequestMapping("/deductStock")
    void deductStock(@RequestParam("pid") Integer pid, @RequestParam("num") Integer num);


}
