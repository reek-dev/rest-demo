package com.example.restdemo.repository;

import com.example.restdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u")
    List<User> getAllUsers();

    boolean existsById(Long id);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

}
