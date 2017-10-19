package com.maxiaogang.sprider;


import com.sun.tools.javac.util.Assert;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RetrivePage {

    private static HttpClient httpClient = HttpClientBuilder.create().useSystemProperties().build();

    public static boolean downloadPage(String path){
        InputStream inputStream = null;
        OutputStream outputStream = null;
        HttpPost httpPost = new HttpPost(path);
        NameValuePair[] postData = new NameValuePair[2];
        postData[0] = new BasicNameValuePair("name","lietu");
        postData[1] = new BasicNameValuePair("password", "*****");
        try {
            HttpResponse response = httpClient.execute(httpPost);
            Assert.check(response != null);
            Assert.check(response.getStatusLine() != null);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode == HttpStatus.SC_OK) {
                inputStream = response.getEntity().getContent();
                //得到文件名
                String filename = path.substring(path.lastIndexOf('/') + 1);
                //获得文件输出流
                outputStream = new FileOutputStream(filename);

                //输出到文件
                int tempByte = -1;
                while ((tempByte=inputStream.read()) > 0){
                    outputStream.write(tempByte);
                }
                //关闭输入输出流
                if (inputStream != null){
                    inputStream.close();
                }
                if (outputStream != null){
                    outputStream.close();
                }
                return true;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        RetrivePage.downloadPage("http://blog.csdn.net/csdnnews/article/details/78280033");
    }
}
