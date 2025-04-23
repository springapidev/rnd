package com.alltimeacademy.ex03simpletokenapisecurity;

//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.web.filter.OncePerRequestFilter;
//import java.util.ArrayList;
//
//public class CustomTokenAuthenticationFilter extends OncePerRequestFilter {
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        String requestURI = request.getRequestURI();
//
//        // Bypass authentication for /login
//        if (requestURI.equals("/login")) {
//            System.out.println("Called at line 24");
//            filterChain.doFilter(request, response);
//            return;
//        }
//        String token = request.getHeader("Authorization");
//        if (token != null && !token.isEmpty()) {
//            // Extract and validate token here (in this case, just checking if it exists)
//            if (token.equals("valid-custom-token")) {
//                // Manually authenticate the user based on token
//                Authentication authentication = new UsernamePasswordAuthenticationToken("user", null, new ArrayList<>());
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            } else {
//                System.out.println("Called at line 35: 401");
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                return;
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}
