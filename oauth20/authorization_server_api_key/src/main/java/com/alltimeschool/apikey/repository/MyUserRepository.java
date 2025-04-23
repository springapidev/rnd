package com.alltimeschool.apikey.repository;

import com.alltimeschool.apikey.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface MyUserRepository extends JpaRepository<MyUser,Long> {
    Optional<MyUser> findByUsernameAndStatus(String username, boolean status);
    boolean existsByUsername(String username);
}
