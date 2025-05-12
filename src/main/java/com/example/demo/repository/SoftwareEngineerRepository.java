package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.SoftwareEngineer;

public interface SoftwareEngineerRepository extends JpaRepository<SoftwareEngineer, Integer>{

    List<SoftwareEngineer> findByName(String name);
}