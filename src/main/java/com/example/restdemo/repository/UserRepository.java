package com.example.restdemo.repository;

import com.example.restdemo.entity.Course;
import com.example.restdemo.entity.Role;
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

    @Query("select u from User u where u.organisation.id = :organizationId")
    List<User> findUsersByOrganizationId(@Param("organizationId") Long organizationId);

    @Query("select u from User u where u.organisation.id = :organisationId and u.role = :role")
    List<User> findUsersByOrgAndRole(@Param("organisationId") Long organisationId, @Param("role") Role role);

    @Query("select u.role, count(u) from User u where u.organisation.id = :orgId group by u.role")
    List<Object[]> countUsersByRole(@Param("orgId") Long orgId);

}
