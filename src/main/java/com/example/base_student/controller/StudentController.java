package com.example.base_student.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.base_student.dto.StudentDto;
import com.example.base_student.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<String> findAll() {
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> findById(@PathVariable Integer id) {
        return studentService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}