package com.maxiaogang.sprider.strategy.impl;

import com.maxiaogang.sprider.strategy.HttpResponseStrategy;
import com.maxiaogang.sprider.strategy.HttpStatusStrategy;
import com.sun.tools.javac.util.Assert;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 301状态处理
 * 302状态处理
 */
@HttpResponseStrategy
public class HttpStatus301Strategy implements HttpStatusStrategy{

    private static Logger logger = LoggerFactory.getLogger(HttpStatus301Strategy.class);

    public boolean supportStrategy(int httpStatusCode) {
        return httpStatusCode == HttpStatus.SC_MOVED_TEMPORARILY || httpStatusCode == HttpStatus.SC_MOVED_PERMANENTLY;
    }

    /**
     *
     * @param response httpResponse
     * @return
     */
    public String doWithResponse(HttpResponse response) {
        Assert.check(response != null && response.getStatusLine() != null, "response不正确!");
        Assert.check(response.getStatusLine().getStatusCode() == HttpStatus.SC_MOVED_PERMANENTLY
                || response.getStatusLine().getStatusCode() == HttpStatus.SC_MOVED_TEMPORARILY, "response状态不正确.");
        Header header = response.getFirstHeader("location");
        if(header != null){
            String redirectUrl = header.getValue();
            // TODO: 2017/10/21 发送请求,做进一步处理
        }else{
            logger.info("URL cannot get location from header.");
        }
        return null;
    }
}
