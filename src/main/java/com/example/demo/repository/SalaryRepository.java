package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.SalaryModel;

public interface SalaryRepository extends JpaRepository<SalaryModel, Long>{
    
}