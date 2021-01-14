package com.demosa.order.api.fallback;

import com.demosa.order.api.ProductApi;
import com.demosa.domain.Product;
import org.springframework.stereotype.Service;

/**
 * 描述 : 产品容错类
 * 作者 : 徐起超
 * 时间 : 2020/12/29 9:48 下午
 */
@Service
public class ProdcutFallBack implements ProductApi {

    @Override
    public Product product(Integer pid) {
        //TODO 容错逻辑
        Product product = new Product();
        product.setPid(-10);
        product.setPname("出现错误");
        return product;
    }


    @Override
    public void deductStock(Integer pid, Integer num) {

    }
}
