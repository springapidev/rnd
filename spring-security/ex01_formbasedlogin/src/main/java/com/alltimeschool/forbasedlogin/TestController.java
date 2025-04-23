package com.alltimeschool.forbasedlogin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {
    @GetMapping
    public String showIndex(){
        return "home";
    }
    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "message", required = false) String message,
                            Model model) {
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("message", message);
        }
        return "login";  // The Thymeleaf login page
    }
    @GetMapping("/manager")
    public String showForManager(){
        return "manager";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";  // This is the view name for the custom error page.
    }
}
