package com.alltimeschool.apibasicsecurity.inmmeory.config;

import jakarta.servlet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;
@Component
public class BasicAuthTokenFilter implements Filter {
    @Autowired
    private InMemoryUserDetailsManager authenticationManager;



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String authorization = servletRequest.getParameter("Authorization");
        if (authorization != null && authorization.startsWith("Basic ")) {
            String credentials = authorization.substring(6);
            String decodeCredentials = new String(Base64.getDecoder().decode(credentials));
            String[] parts = decodeCredentials.split(":");
            if (parts.length == 2) {
                String username = parts[0];
                String password = parts[1];
                try {
                    Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
              //      Authentication authenticated = authenticationManager.authenticate(authentication);
              //      SecurityContextHolder.getContext().setAuthentication(authenticated);
                } catch (BadCredentialsException e) {
                    servletResponse.getWriter().print("Invalid Credentials");
                }
            }

        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
