package com.maxiaogang.sprider.strategy.impl;

import com.maxiaogang.sprider.strategy.HttpStatusStrategy;
import com.sun.tools.javac.util.Assert;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 对HTTP状态为200的结果进行处理
 */
public class HttpStatus200Strategy implements HttpStatusStrategy {

    private static Logger logger = LoggerFactory.getLogger(HttpStatus200Strategy.class);

    public boolean supportStrategy(int httpStatusCode) {
        return httpStatusCode == HttpStatus.SC_OK;
    }

    /**
     * 处理200状态的结果
     * @param response httpResponse
     * @return
     */
    public String doWithResponse(HttpResponse response) {
        Assert.check(response != null && response.getStatusLine() != null, "response不正确!");
        Assert.check(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK, "response状态不正确!");
        String result = "";
        try {
            InputStream inputStream = response.getEntity().getContent();
            StringWriter writer = new StringWriter();
            int tempByte = -1;
            while ((tempByte = inputStream.read()) > 0){
                writer.write(tempByte);
            }
            result = writer.getBuffer().toString();
            writer.close();
        } catch (IOException e) {
            logger.error("Cannot get inputStream from response", e);
        }
        return result;
    }
}
