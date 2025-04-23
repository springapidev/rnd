package com.alltimeschool.forbasedlogin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.util.UriUtils;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails adminUser = User.withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();
        UserDetails managerUser = User.withUsername("manager")
                .password(passwordEncoder.encode("manager"))
                .roles("MANAGER")
                .build();

        return new InMemoryUserDetailsManager(adminUser, managerUser);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/manager").hasRole("MANAGER")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/", true)
                        .failureHandler(authenticationFailureHandler())
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // This is the default, but explicitly defining it
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID") // Ensures session is fully cleared
                        .permitAll()
                )
                .rememberMe(me->
                        me.key("mySecretKey")
                                .rememberMeParameter("remember-me")
                                .tokenValiditySeconds(86400)
                                .useSecureCookie(true)
                        )

                .exceptionHandling(exeception ->
                        exeception.accessDeniedHandler(new CustomAccessDeniedHandler())
                )
                .sessionManagement(config->
                        config.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                                .sessionConcurrency(concurrencyControlConfigurer ->
                                        concurrencyControlConfigurer.maximumSessions(1)
                                                .maxSessionsPreventsLogin(true)
                                )
                )
        ;

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return webSecurity -> webSecurity.ignoring().requestMatchers("/css/**", "/js/**", "/images/**","login?error/**");
    }

  @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, exception) -> {
            String errorMessage = "You are already logged in from another browser";
            System.out.println("Redirecting to login page with message: " + errorMessage); // Debug message
            response.sendRedirect("/login?error=true&message=" + UriUtils.encode(errorMessage, "UTF-8"));
        };
    }
}
