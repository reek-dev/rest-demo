package com.example.restdemo.repository;

import com.example.restdemo.entity.Role;
import com.example.restdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

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

    @Query("select count(u) from User u where u.organisation.id = :organisationId and u.role = :student")
    Long countStudentsByOrganisation(@Param("organisationId") Long organisationId, @Param("student") Role student);


    @Query("select count(u) from User u where u.organisation.id = :organisationId and u.role = :teacher")
    Long countTeachersByOrganisation(@Param("organisationId") Long organisationId, @Param("teacher") Role teacher);

}
