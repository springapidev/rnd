package com.alltimeschool.apikey.config;
//
//import com.alltimeschool.apikey.entity.*;
//import com.alltimeschool.apikey.enums.AuthMethod;
//import com.alltimeschool.apikey.enums.ClientResponseType;
//import com.alltimeschool.apikey.enums.ClientType;
//import com.alltimeschool.apikey.enums.GrantType;
//import com.alltimeschool.apikey.repository.ClientPostLogoutRedirectUriRepsoitory;
//import com.alltimeschool.apikey.repository.ClientRedirectUriRepository;
//import com.alltimeschool.apikey.repository.ClientScopeRepository;
//import com.alltimeschool.apikey.repository.MyUserRepository;
//import com.alltimeschool.apikey.service.ClientDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//import java.util.UUID;
//
//@Component
//public class AdminUserInitializer implements CommandLineRunner {
//    @Autowired
//    private MyUserRepository myUserRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private ClientDetailsService clientDetailsService;
//    @Autowired
//    private ClientScopeRepository clientScopeRepository;
//    @Autowired
//    private ClientPostLogoutRedirectUriRepsoitory clientPostLogoutRedirectUriRepsoitory;
//    @Autowired
//    private ClientRedirectUriRepository clientRedirectUriRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        Optional<MyUser> myUser = myUserRepository.findByUsernameAndStatus("admin", true);
//        if (!myUserRepository.existsByUsername("admin")) {
//            MyUser user = new MyUser();
//            user.setUsername("admin");
//            user.setPassword(passwordEncoder.encode("admin"));
//            user.setRole("ADMIN");
//            user.setStatus(true);
//            this.myUserRepository.save(user);
//        }
//        // Creating redirect URIs
//        ClientRedirectUri redirectUri1 = new ClientRedirectUri("http://localhost:8081/callback");
//        ClientRedirectUri redirectUri2 = new ClientRedirectUri("http://localhost:8081/authorized");
//        clientRedirectUriRepository.saveAll(List.of(redirectUri1, redirectUri2));
//
//        // Creating post logout redirect URIs
//        ClientPostLogoutRedirectUri logoutRedirectUri1 = new ClientPostLogoutRedirectUri("http://localhost:8081/logout-callback");
//        ClientPostLogoutRedirectUri logoutRedirectUri2 = new ClientPostLogoutRedirectUri("http://localhost:8081/logout-success");
//        clientPostLogoutRedirectUriRepsoitory.saveAll(List.of(logoutRedirectUri1, logoutRedirectUri2));
//
//        // Creating scopes
//        ClientScope scope1 = new ClientScope("openid");
//        ClientScope scope2 = new ClientScope("profile");
//        ClientScope scope3 = new ClientScope("email");
//        clientScopeRepository.saveAll(List.of(scope1, scope2, scope3));
//
//        // Creating client details
//        ClientDetails clientDetails1 = new ClientDetails();
//        clientDetails1.setId(UUID.randomUUID().toString());
//        clientDetails1.setClientId("client1");
//        clientDetails1.setClientSecret("secret1");
//        clientDetails1.setEnabled(true);
//        clientDetails1.setRequireAuthorizationConsent(true);
//        clientDetails1.setGrantTypes(Set.of(GrantType.AUTHORIZATION_CODE, GrantType.REFRESH_TOKEN));
//        clientDetails1.setTokenEndpointAuthMethods(Set.of(AuthMethod.CLIENT_SECRET_BASIC));
//        clientDetails1.setClientResponseTypes(Set.of(ClientResponseType.CODE, ClientResponseType.TOKEN));
//        clientDetails1.setAccessTokenLifetime(3600);
//        clientDetails1.setRefreshTokenLifetime(1209600);
//        clientDetails1.setClientName("Client One");
//        clientDetails1.setClientType(ClientType.CONFIDENTIAL);
//        clientDetails1.setClientDescription("Sample client 1");
//        clientDetails1.setRedirectUris(List.of(redirectUri1, redirectUri2));
//        clientDetails1.setScopes(List.of(scope1, scope2));
//        clientDetails1.setPostLogoutRedirectUris(List.of(logoutRedirectUri1, logoutRedirectUri2));
//
//        clientDetailsService.saveClientDetails(clientDetails1);
//
//        // Another client for testing
//        ClientDetails clientDetails2 = new ClientDetails();
//        clientDetails2.setId(UUID.randomUUID().toString());
//        clientDetails2.setClientId("client2");
//        clientDetails2.setClientSecret("secret2");
//        clientDetails2.setEnabled(true);
//        clientDetails2.setRequireAuthorizationConsent(false);
//        clientDetails2.setGrantTypes(Set.of(GrantType.CLIENT_CREDENTIALS));
//        clientDetails2.setTokenEndpointAuthMethods(Set.of(AuthMethod.CLIENT_SECRET_BASIC));
//        clientDetails2.setClientResponseTypes(Set.of(ClientResponseType.CODE));
//        clientDetails2.setAccessTokenLifetime(3600);
//        clientDetails2.setRefreshTokenLifetime(1209600);
//        clientDetails2.setClientName("Client Two");
//        clientDetails2.setClientType(ClientType.PUBLIC);
//        clientDetails2.setClientDescription("Sample client 2");
//        clientDetails2.setRedirectUris(List.of(redirectUri2));
//        clientDetails2.setScopes(List.of(scope1, scope3));
//        clientDetails2.setPostLogoutRedirectUris(List.of(logoutRedirectUri2));
//
//        clientDetailsService.saveClientDetails(clientDetails2);
//
//        System.out.println("Sample client data inserted successfully.");
//    }
//}
