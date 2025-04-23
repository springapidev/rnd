package com.alltimeschool.forbasedlogin;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class UserPrinter {
    private final UserDetailsService userDetails;

    public UserPrinter(UserDetailsService userDetails) {
        this.userDetails = userDetails;
    }
    public void printUserPassword(){
        System.out.println(userDetails.loadUserByUsername("admin"));
    }
}
