package com.maxiaogang.sprider.core;

import org.apache.http.HttpResponse;

public interface HttpStatusDispatcher {
    /**
     * http状态分发
     *
     * @param httpStatus http状态码
     */
    void doDispatcher(int httpStatus, HttpResponse response);
}
