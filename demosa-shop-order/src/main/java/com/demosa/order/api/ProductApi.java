package com.demosa.order.api;

import com.demosa.apis.BaseProductApi;
import com.demosa.order.api.fallback.ProdcutFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述 : 服务接口
 * 作者 : 徐起超
 * 时间 : 2020/12/28 2:27 下午
 */
@FeignClient(
        value = "service-product",
        path = "product",
        //fallback = ProdcutFallBack.class
        fallbackFactory = ProdcutFallBackFactory.class
)
public interface ProductApi extends BaseProductApi {


}
