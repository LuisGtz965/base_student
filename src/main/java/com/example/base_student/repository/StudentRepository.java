package com.example.base_student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.base_student.model.StudentModel;

@Repository 
public interface StudentRepository extends JpaRepository<StudentModel, Integer> {

}