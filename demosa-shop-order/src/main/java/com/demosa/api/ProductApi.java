package com.demosa.api;

import com.demosa.apis.BaseProductApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/28 2:27 下午
 */
@FeignClient(value = "service-product", path = "product")
public interface ProductApi extends BaseProductApi {


}
