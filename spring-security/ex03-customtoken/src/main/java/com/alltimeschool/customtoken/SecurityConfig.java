package com.alltimeschool.customtoken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private TokenService tokenService; // Inject the TokenService

    @Autowired
    private CustomBearerTokenFilter customBearerTokenFilter; // Inject the custom filter

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails adminUser = User
                .withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();
        UserDetails manager = User
                .withUsername("manager")
                .password(passwordEncoder.encode("manager"))
                .roles("MANAGER")
                .build();

        return new InMemoryUserDetailsManager(adminUser, manager);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                  .csrf(AbstractHttpConfigurer::disable)// Not Recommneded
//                .csrf(csrf -> csrf
//                        //     .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())//Only recommended if you need to access csrf token
//                        .csrfTokenRepository(new HttpSessionCsrfTokenRepository())//Only recommended
//                )// Not Recommended
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()  // Allow all requests to /auth/*
                        .requestMatchers(HttpMethod.GET,"/auth/signup").permitAll() // Ensure signup is also accessible without token
                        .requestMatchers(HttpMethod.POST,"/auth/signup").permitAll() // Ensure signup is also accessible without token
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/manager").hasRole("MANAGER")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               // .exceptionHandling(exception -> exception.accessDeniedHandler(new CustomAccessDeniedhander()))
                .build();
        httpSecurity.addFilterBefore(customBearerTokenFilter, UsernamePasswordAuthenticationFilter.class).build();
        return httpSecurity.build();
    }

    @Bean
    public CustomBearerTokenFilter customBearerTokenFilter() {
        return new CustomBearerTokenFilter(tokenService);
    }
}
