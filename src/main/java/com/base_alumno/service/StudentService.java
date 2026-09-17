package com.base_alumno.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.base_alumno.dto.StudentDto;
import com.base_alumno.repository.StudentRepository;

@Service 
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    
    public List <StudentDto> findAll() {
        return studentRepository.findAll().stream().map(student -> student.toDto()).toList();
    }

}
