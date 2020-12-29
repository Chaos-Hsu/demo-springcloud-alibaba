package com.demosa.api.fallback;

import com.demosa.api.ProductApi;
import com.demosa.domain.Product;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 描述 : 容错工厂
 * 作者 : 徐起超
 * 时间 : 2020/12/29 9:59 下午
 */
@Slf4j
@Component
public class ProdcutFallBackFactory implements FallbackFactory<ProductApi> {

    /**
     * 容错处理
     *
     * @param throwable
     * @return
     */
    @Override
    public ProductApi create(Throwable throwable) {
        return p -> {
            log.error("{}", throwable);
            Product product = new Product();
            product.setPid(-100);
            product.setPname("容错工厂,pid=" + p);
            return product;
        };
    }
}
