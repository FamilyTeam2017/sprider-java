package com.maxiaogang.sprider.strategy.impl;

import com.maxiaogang.sprider.strategy.HttpStatusStrategy;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

public class HttpStatus500Strategy implements HttpStatusStrategy{
    public boolean supportStrategy(int httpStatusCode) {
        return httpStatusCode == HttpStatus.SC_INTERNAL_SERVER_ERROR;
    }

    public String doWithResponse(HttpResponse response) {
        return null;
    }
}
