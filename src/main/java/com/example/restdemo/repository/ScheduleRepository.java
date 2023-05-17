package com.example.restdemo.repository;

import com.example.restdemo.entity.City;
import com.example.restdemo.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("select s from Schedule s where s.organisation.id = :organisationId")
    List<Schedule> fetchSchedulesByOrg(@Param("organisationId") Long organisationId);
}
