package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.SalaryModel;
import com.example.demo.repository.SalaryRepository;

@Service
public class SalaryService {
	@Autowired
	public SalaryRepository repository;
	
	public List<SalaryModel> getSalary(){
		return repository.findAll();
	}

	public SalaryModel insert(SalaryModel salaryModel) {
        return repository.save(salaryModel);
    }
}
