package com.example.attacks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
    @MockitoBean
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MockMvc mockMvc;
    private MyUser testUser;

    @BeforeEach
    void testSetup(){
        testUser=new MyUser();
        testUser.setUsername("validUser");
        testUser.setPassword(passwordEncoder.encode("securePassword"));
    }
    @Test
    public void testValidLogin() throws Exception {
        Mockito.when(userRepository.findByUsername("validUser")).thenReturn(Optional.of(testUser));

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"validUser\", \"password\": \"securePassword\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Login Successful"));
    }

    @Test
    public void testInvalidLogin() throws Exception {
        Mockito.when(userRepository.findByUsername("invalidUser")).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"invalidUser\", \"password\": \"wrongPassword\"}"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.content().string("Invalid credentials"));
    }

    @Test
    public void testSQLInjectionAttempt() throws Exception {
        String sqlInjection = "' OR '1'='1";

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"" + sqlInjection + "\", \"password\": \"any\"}"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized()) // Should fail
                .andExpect(MockMvcResultMatchers.content().string("Invalid credentials"));
    }
}