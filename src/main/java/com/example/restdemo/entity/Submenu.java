package com.example.restdemo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "submenu")
public class Submenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_menu_id")
    private Integer sub_menu_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(name = "sub_menu_name")
    private String sub_menu_name;

    @Column(name = "sub_menu_link")
    private String sub_menu_link;

    @Column(name = "sub_menu_icon")
    private String sub_menu_icon;

    @Column(name = "Ordering")
    private Integer ordering;

    @Column(name = "IsActive")
    private Boolean active;

    @Column(name = "IsAdmin")
    private Boolean admin;

    @Column(name = "IsTeacher")
    private Boolean teacher;

    @Column(name = "IsStudent")
    private Boolean student;
}
