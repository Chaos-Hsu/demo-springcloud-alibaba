package com.demosa.exception;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 描述 : 自定义返回异常
 * 作者 : 徐起超
 * 时间 : 2020/12/29 8:52 下午
 */
@Component
public class ExceptionHandlerPage implements UrlBlockHandler {


    @Override
    public void blocked(HttpServletRequest request, HttpServletResponse response, BlockException e) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        ResponseData responseData = null;
        if (e instanceof FlowException) {
            responseData = new ResponseData(-1, "接口限流");
        }
        if (e instanceof DegradeException) {
            responseData = new ResponseData(-2, "接口降级");
        }
        if (responseData != null) {
            response.getWriter().write(JSON.toJSONString(responseData));
        }
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ResponseData {
    private Integer code;
    private String message;
}
