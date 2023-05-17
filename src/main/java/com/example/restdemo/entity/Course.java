package com.example.restdemo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    @JsonBackReference
    @JsonIgnore
    private Organisation organisation;

    @Column(name = "name", length = 100)
    private String courseName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    @JsonIgnore
    private CourseCategory category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> associatedUsers = new HashSet<>();


    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    private Set<Schedule> schedules = new HashSet<>();

    @Column(name = "description", length = 500)
    private String courseDescription;

    @Column(name = "duration")
    private Integer courseDuration;

    @Enumerated(EnumType.STRING)
    @Column(name = "level", length = 20)
    private CourseLevel courseLevel;

    @Column(name = "fees")
    private Integer courseFees;

    @Column(name = "maxEnroll")
    private Integer enrollment;

    @Column(name = "prerequisites")
    private String prerequisites;

    @Enumerated(EnumType.STRING)
    @Column(name = "format", length = 10)
    private CourseFormat courseFormat;

    @Column(name = "startDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate startDate;

    @Column(name = "endDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate endDate;

}
