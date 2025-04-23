package com.example.attacks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser,Long> {
    Optional<MyUser> findByUsername(String password);
    //Secured
    //Optional<MyUser> findByUsernameAndPassword(String username, String password);
    //Secured
    @Query("SELECT u FROM MyUser u WHERE u.username = :username AND u.password = :password")
    MyUser findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    // ❌ BAD: Vulnerable to SQL Injection
//    @Query("SELECT u FROM User u WHERE u.username = ?1 AND u.password = ?2")
//    MyUser findByUsernameAndPassword(String username, String password);

}
