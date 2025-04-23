package com.alltimeschool.customtoken;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSecurityRestController {
    @GetMapping("/signup")
    public String signup(){
        return "signup..........";
    }
    @GetMapping("/admin")
    public String admin(){
        return "admin..........";
    }
    @GetMapping("/manager")
    public String manager(){
        return "manager..........";
    }

    @GetMapping("/access-denied")
    public ResponseEntity<String> accessDenied(){
        return new ResponseEntity<>("access-denied..........", HttpStatusCode.valueOf(403));
    }


}
