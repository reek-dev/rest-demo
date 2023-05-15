package com.example.restdemo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "menu_name")
    private String menu_name;

    @Column(name = "menu_link")
    private String menu_link;

    @Column(name = "menu_icon")
    private String menu_icon;

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

    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Submenu> submenus;

}
