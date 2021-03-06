package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final StudentRepository studentRepository;

    public StudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @GetMapping("{id}")
    public Student findStudent(@PathVariable Long id) {
        return studentService.findStudent(id);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public Student editStudent(@RequestBody Student student) {
        return studentService.editStudent(student.getId(), student);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> removeStudent(@PathVariable Long id) {
        studentService.removeStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/age/{age}")
    public Collection<Student> findStudentsByAge(@PathVariable int age) {
        return studentService.findStudentsByAge(age);
    }

    @GetMapping(params = {"min", "max"})
    public Collection<Student> findStudentsByAgeBetween(@RequestParam int min, @RequestParam int max) {
        return studentService.findStudentsByAgeBetween(min, max);
    }

    @GetMapping("all")
    public Collection<Student> findAllStudent() {
        return studentRepository.findAll();
    }

    @GetMapping("count")
    public Integer findCountStudent() {
        return studentService.findCountStudent();
    }

    @GetMapping("avg")
    public Float findAvgAgeStudent() {
        return studentService.findAvgAgeStudent();
    }
    @GetMapping("5student")
    public Collection<String> findLastFiveStudent() {
        return studentService.findLastFiveStudent();
    }
}
