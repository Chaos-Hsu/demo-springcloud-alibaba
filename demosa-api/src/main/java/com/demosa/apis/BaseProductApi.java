package com.demosa.apis;

import com.demosa.domain.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述 : 产品基础API接口
 * 作者 : 徐起超
 * 时间 : 2020/12/28 2:27 下午
 */
public interface BaseProductApi {

    /**
     * 获取唯一数据
     *
     * @param pid
     * @return
     */
    @RequestMapping("/{pid}")
    Product product(@PathVariable("pid") Integer pid);

}
