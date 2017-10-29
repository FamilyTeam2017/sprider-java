package com.maxiaogang.sprider.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages = "com.maxiaogang.sprider.web")
public class SpriderWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpriderWebApplication.class);
    }
}
