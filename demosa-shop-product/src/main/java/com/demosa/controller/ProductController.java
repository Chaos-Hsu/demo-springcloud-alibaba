package com.demosa.controller;

import com.alibaba.fastjson.JSON;
import com.demosa.domain.Product;
import com.demosa.service.ProdcutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/25 11:59 上午
 */
@RestController
@Slf4j
public class ProductController {

    @Autowired
    private ProdcutService prodcutService;

    @RequestMapping("/product/{pid}")
    public Product product(@PathVariable("pid") Integer pid) {
        log.info("进行{}号商品查询", pid);
        Product product = prodcutService.findById(pid);
        log.info("查询成功,内容为:{}", JSON.toJSON(product));
        return product;
    }


}
