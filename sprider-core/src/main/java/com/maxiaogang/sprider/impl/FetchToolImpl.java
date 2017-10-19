package com.maxiaogang.sprider.impl;

import com.maxiaogang.sprider.FetchTool;
import com.sun.tools.javac.util.Assert;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FetchToolImpl implements FetchTool{

    private static Logger logger = LoggerFactory.getLogger(FetchToolImpl.class);

    public synchronized File fetchPage(String urlPath, String filePath) {
        logger.info("urlPath:{},filePath:{}", urlPath, filePath);
        File resultFile = new File(filePath);
        if(!resultFile.exists()){
            try {
                resultFile.createNewFile();
            } catch (IOException e) {
                logger.error("创建文件失败.", e);
            }
        }
        HttpClient httpClient = HttpClientBuilder.create().useSystemProperties().build();
        HttpGet httpGet = new HttpGet(urlPath);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            Assert.check(response != null);
            Assert.check(response.getStatusLine() != null);
            int status = response.getStatusLine().getStatusCode();
            if(status == HttpStatus.SC_OK){
                int tempByte = -1;
                InputStream inputStream = response.getEntity().getContent();
                FileOutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream(resultFile);
                } catch (FileNotFoundException e) {
                    logger.error("文件不存在.", e);
                }
                while ((tempByte=inputStream.read()) > 0){
                    outputStream.write(tempByte);
                }
                //关闭输入输出流
                inputStream.close();
                if (outputStream != null){
                    outputStream.close();
                }
            }else {
                logger.info("path:{};status:{}", urlPath, status);
                return null;
            }

        } catch (IOException e) {
            logger.error("http执行失败", e);
        }

        return resultFile;
    }
}
