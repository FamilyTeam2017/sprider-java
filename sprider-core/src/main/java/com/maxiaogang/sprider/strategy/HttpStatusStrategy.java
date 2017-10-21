package com.maxiaogang.sprider.strategy;

import org.apache.http.HttpResponse;

import java.io.File;
import java.io.OutputStream;

/**
 * 对HTTP状态进行处理
 */
public interface HttpStatusStrategy {

    /**
     * 判断是否支持该策略
     * @param httpStatusCode http状态
     * @return
     */
    boolean supportStrategy(int httpStatusCode);

    /**
     * 处理response
     * @param response httpResponse
     * @return
     */
    String doWithResponse(HttpResponse response);
}
