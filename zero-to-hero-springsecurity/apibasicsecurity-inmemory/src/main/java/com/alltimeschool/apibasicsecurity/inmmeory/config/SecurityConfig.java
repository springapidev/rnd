package com.alltimeschool.apibasicsecurity.inmmeory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class SecurityConfig {



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth-> auth
                .requestMatchers("/").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/manager").hasRole("MANAGER")
                .requestMatchers("/user").hasRole("USER")
                .requestMatchers("/both").hasAnyRole("ADMIN","MANAGER")
                .requestMatchers("/all").hasAnyRole("ADMIN","MANAGER","USER")
                .anyRequest().authenticated()
        ).httpBasic(Customizer.withDefaults())
                .sessionManagement(policy-> policy.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            //    .addFilterBefore(new TokenAuthenticationFilter(), BasicAuthenticationFilter.class)
//                .addFilter(basicAuthTokenFilter)
        ;
        return http.build();
    }

}
