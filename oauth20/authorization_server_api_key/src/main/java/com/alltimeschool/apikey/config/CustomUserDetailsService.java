package com.alltimeschool.apikey.config;

import com.alltimeschool.apikey.entity.MyUser;
import com.alltimeschool.apikey.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private MyUserRepository myUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = this.myUserRepository.findByUsernameAndStatus(username, true).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        return new User(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_" + user.getRole()));
    }
}
