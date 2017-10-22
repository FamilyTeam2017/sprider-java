package com.maxiaogang.sprider.core.impl;

import com.maxiaogang.sprider.core.HttpStatusDispatcher;
import com.maxiaogang.sprider.strategy.HttpStatusStrategy;
import com.maxiaogang.sprider.strategy.impl.HttpStatus200Strategy;
import com.maxiaogang.sprider.strategy.impl.HttpStatus301Strategy;
import com.maxiaogang.sprider.strategy.impl.HttpStatus302Strategy;
import com.maxiaogang.sprider.strategy.impl.HttpStatus500Strategy;
import com.sun.tools.javac.util.Assert;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.HttpResponse;

import java.util.ArrayList;
import java.util.List;

public class HttpStatusDispatcherImpl implements HttpStatusDispatcher{

    private static List<HttpStatusStrategy> strategyList = new ArrayList<HttpStatusStrategy>();

    static {
        strategyList.add(new HttpStatus200Strategy());
        strategyList.add(new HttpStatus301Strategy());
        strategyList.add(new HttpStatus302Strategy());
        strategyList.add(new HttpStatus500Strategy());
    }

    /**
     * httpStatus状态分发，并且对结果进行处理
     * @param httpStatus http状态码
     * @param response 返回结果集
     */
    @Override
    public void doDispatcher(int httpStatus, HttpResponse response) {
        Assert.check(CollectionUtils.isNotEmpty(strategyList));
        HttpStatusStrategy httpStatusStrategy = selectHttpStatusStrategy(httpStatus);
        String result = httpStatusStrategy.doWithResponse(response);
        // TODO: 2017/10/22 result结果处理,比如存储在某个位置,进行落地
    }

    /**
     * 选择符合状态码的策略
     * @param httpStatusCode http状态码
     * @return
     */
    private HttpStatusStrategy selectHttpStatusStrategy(int httpStatusCode){
        HttpStatusStrategy httpStatusStrategy = null;
        for (HttpStatusStrategy temp: strategyList) {
            if (temp.supportStrategy(httpStatusCode)){
                httpStatusStrategy = temp;
                break;
            }
        }
        return httpStatusStrategy;
    }
}
