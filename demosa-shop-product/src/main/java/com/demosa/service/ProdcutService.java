package com.demosa.service;

import com.demosa.domain.Product;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/25 4:38 下午
 */
public interface ProdcutService {

    /**
     * 根据ID查询
     *
     * @param pid
     * @return
     */
    Product findById(Integer pid);
}
