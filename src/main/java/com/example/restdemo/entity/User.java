package com.example.restdemo.entity;

import com.example.restdemo.util.GenderAttributeConverter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
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
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    @JsonBackReference
    @JsonIgnore
    private Organisation organisation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    @JsonBackReference
    @JsonIgnore
    private State state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    @JsonBackReference
    @JsonIgnore
    private City city;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "firstname", updatable = true, nullable = false, length = 50)
    private String firstName;

    @Column(name = "lastname", updatable = true, nullable = false, length = 50)
    private String lastName;

    @Column(name = "phoneNo", nullable = false, updatable = true)
    private String phone;

    @Column(name = "address")
    private String address;

    @Convert(converter = GenderAttributeConverter.class)
    @Column(name = "gender", length = 2, nullable = false)
    private Gender gender;

    @Column(name = "DOB")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dob;

    @Column(name = "joiningDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate joiningDate;

    @ManyToMany(mappedBy = "associatedUsers", fetch = FetchType.LAZY)
    private Set<Course> assignedCourses = new HashSet<>();


    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    private Set<Schedule> schedules = new HashSet<>();

    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    private Set<Feedback> feedbacks = new HashSet<>();

}
