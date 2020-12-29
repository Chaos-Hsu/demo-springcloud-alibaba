package com.demosa.api;

import com.demosa.api.fallback.ProdcutFallBack;
import com.demosa.api.fallback.ProdcutFallBackFactory;
import com.demosa.apis.BaseProductApi;
import com.demosa.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
//public interface ProductApi {



}
