package com.maxiaogang.sprider.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class IndexController {
    @RequestMapping("/")
    @ResponseBody
    public String home(){
        return "宝宝12345";
    }

}
