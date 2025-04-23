package com.alltimeschool.apibasicsecurity.inmmeory.config;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Collections;
//import java.util.stream.Collectors;
//
//public class TokenAuthenticationFilter extends OncePerRequestFilter {
//    private static final String TOKEN_HEADER = "X-Auth-Token"; // Name of the token header
//    private static final String VALID_TOKEN = "mysecrettoken"; // Your custom token
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//// Retrieve the token from the header
//        String token = request.getHeader(TOKEN_HEADER);
//
//        if (token == null || !token.equals(VALID_TOKEN)) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("Invalid or missing token");
//            return;
//        }else {
//            Authentication authentication = new UsernamePasswordAuthenticationToken("user", null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//
//        // Proceed with the next filter in the chain
//        filterChain.doFilter(request, response);
//    }
//}
