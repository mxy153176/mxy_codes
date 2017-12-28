package com.net.gongdan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping(value = {"/", "/index", "/index.htm"})
    public String index() {
        return "index";
    }


    @RequestMapping(value = {"/login"})
    public String login() {
        return "login";
    }


    @RequestMapping(value = {"/signUp"})
    public String signUp() {
        return "sign-up";
    }
}

