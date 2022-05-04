package com.example.preproj4.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

    @RequestMapping("/auth/login")
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping("/index")
    public String getMainPage() {
        return "index";
    }
}
