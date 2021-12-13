package com.tasty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
//    配置受首页地址
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
