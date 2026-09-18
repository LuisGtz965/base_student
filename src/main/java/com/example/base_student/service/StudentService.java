package com.example.base_student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.base_student.dto.StudentDto;
import com.example.base_student.repository.StudentRepository;

@Service 
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    
    public List <StudentDto> findAll() {
        return studentRepository.findAll().stream().map(student -> student.toDto()).toList();
    }

    public Optional<StudentDto> findById(Integer id) {
        return studentRepository.findById(id).map(student -> student.toDto());
    }

}