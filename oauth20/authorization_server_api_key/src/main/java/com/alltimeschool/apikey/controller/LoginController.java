package com.alltimeschool.apikey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
   @GetMapping("/")
    public String index(){
       return "home";
   }

    @GetMapping("/access-denied")
    public String deniedAccess(){
        return "access-denied";
    }
}
