package com.example.restdemo.entity;

import java.time.LocalDateTime;

public class CoachingSessions {

    private Long id;
    private LocalDateTime date;
    private Integer time;
    private Integer duration;
    private Course course;
    private User instructor;
}
