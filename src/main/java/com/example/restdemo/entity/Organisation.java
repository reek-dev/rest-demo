package com.example.restdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "organisation")
public class Organisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "organisation", fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    private Set<Course> courses = new HashSet<>();

    @OneToMany(mappedBy = "organisation", fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    @ManyToMany(mappedBy = "organisations", fetch = FetchType.LAZY)
    private Set<CourseCategory> courseCategories = new HashSet<>();


    @OneToMany(mappedBy = "organisation", fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    private Set<Schedule> schedules = new HashSet<>();


    @OneToMany(mappedBy = "organisation", fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    private Set<Feedback> feedbacks = new HashSet<>();


    @OneToMany(mappedBy = "organisation", fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    private Set<Assignment> assignments = new HashSet<>();


}
