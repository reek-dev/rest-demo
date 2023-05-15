package com.example.restdemo.repository;

import com.example.restdemo.entity.Submenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmenuRepository extends JpaRepository<Submenu, Integer> {
}
