package com.maxiaogang.sprider;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class DemoTest {

    public void test(){
        String path = "http://geek.csdn.net/news/detail/239738";
        try {
            URL pageURL = new URL(path);
            InputStream stream = pageURL.openStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
