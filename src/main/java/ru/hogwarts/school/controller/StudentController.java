package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.CountStudents;
import ru.hogwarts.school.model.AvgAgeStudents;
import ru.hogwarts.school.model.LastFiveStudents;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
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
        return studentService.findAllStudent();
    }

    @GetMapping("count")
    public Collection<CountStudents> findCountStudent() {
        return studentService.findCountStudent();
    }

    @GetMapping("avg")
    public Collection<AvgAgeStudents> findAvgAgeStudent() {
        return studentService.findAvgAgeStudent();
    }
    @GetMapping("5student")
    public Collection<LastFiveStudents> findLastFiveStudent() {
        return studentService.findLastFiveStudent();
    }
}
