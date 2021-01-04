package com.demosa.predicate;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 描述 : 自定义路由断言
 * 作者 : 徐起超
 * 时间 : 2021/1/4 7:05 下午
 */
@Component
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {

    /**
     * 构造函数
     */
    public AgeRoutePredicateFactory() {
        super(AgeRoutePredicateFactory.Config.class);
    }

    /**
     * 读取配置文件的参数,赋值参数
     *
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        //顺序跟配置文件的顺序一致
        return Arrays.asList("minAge", "maxAge");
    }


    /**
     * 断言逻辑
     *
     * @param config
     * @return
     */
    public Predicate<ServerWebExchange> apply(AgeRoutePredicateFactory.Config config) {
        return (serverWebExchange) -> {
            //接收前台参数
            String age = serverWebExchange.getRequest().getQueryParams().getFirst("age");
            //先判空
            if (!StringUtils.isEmpty(age)) {
                //不为空,路由逻辑判断
                int i = Integer.parseInt(age);
                if (i < config.getMaxAge() && i > config.getMinAge()) {
                    return true;
                }
            }
            return false;
        };
        //return new Predicate<ServerWebExchange>() {
        //    @Override
        //    public boolean test(ServerWebExchange serverWebExchange) {
        //        //接收前台参数
        //        String age = serverWebExchange.getRequest().getQueryParams().getFirst("age");
        //        //先判空
        //        if (!StringUtils.isEmpty(age)) {
        //            //不为空,路由逻辑判断
        //            int i = Integer.parseInt(age);
        //            if (i < config.getMaxAge() && i > config.getMinAge()) {
        //                return true;
        //            }
        //        }
        //        return false;
        //    }
        //};
    }

    /**
     * 配置类
     */
    @Data
    @NoArgsConstructor
    public static class Config {

        private int minAge;
        private int maxAge;

    }
}
