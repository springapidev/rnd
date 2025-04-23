package com.alltimeschool.apibasicsecurity.inmmeory.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping(value = "/")
    public String home(){
        return "Fully Secure Url";
    }
    @GetMapping(value = "/admin")
    public String admin(){
        return "admin area";
    }
    @GetMapping(value = "/manager")
    public String manager(){
        return "manager area";
    }
    @GetMapping(value = "/user")
    public String user(){
        return "user area";
    }
    @GetMapping(value = "/both")
    public String both(){
        return "Admin and Manager have access in this area";
    }
    @GetMapping(value = "/all")
    public String all(){
        return "Admin, Manager and User have only access in this area";
    }

}
