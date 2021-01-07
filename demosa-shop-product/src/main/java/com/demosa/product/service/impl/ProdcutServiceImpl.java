package com.demosa.product.service.impl;

import com.demosa.product.dao.ProductDao;
import com.demosa.domain.Product;
import com.demosa.product.service.ProdcutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/25 4:39 下午
 */
@Service
public class ProdcutServiceImpl implements ProdcutService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product findById(Integer pid) {
        Optional<Product> byId = productDao.findById(pid);
        return byId.get();
    }
}
