package com.demosa.api;

import com.demosa.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/28 2:27 下午
 */
@FeignClient(value = "service-product", path = "product")
public interface ProductApi {

    @RequestMapping("/{pid}")
    Product product(@PathVariable("pid") Integer pid);

}
