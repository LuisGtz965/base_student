package com.base_alumno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base_alumno.model.StudentModel;

@Repository 
public interface StudentRepository extends JpaRepository<StudentModel, Integer> {

}