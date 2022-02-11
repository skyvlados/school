package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentServiceImpl studentServiceImpl;

    public StudentController(StudentServiceImpl studentServiceImpl) {
        this.studentServiceImpl = studentServiceImpl;
    }

//    @GetMapping()
//    public Student getStudent(@RequestParam Long id) {
//        return studentServiceImpl.findStudent(id);
//    }

    @GetMapping()
    public Collection<Student> findStudent(@RequestParam(required = false) String name, @RequestParam(required = false) String namePart) {
        if (name != null && !name.isBlank()) {
            return studentServiceImpl.findStudentName(name);
        }
        if (namePart != null && !namePart.isBlank()) {
            return studentServiceImpl.findByNamePart(name);
        }
        return studentServiceImpl.findAllStudent();
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentServiceImpl.createStudent(student);
    }

    @PutMapping
    public Student editStudent(@RequestBody Student student) {
        return studentServiceImpl.editStudent(student.getId(),student);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> removeStudent(@PathVariable Long id) {
        studentServiceImpl.removeStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{age}")
    public Collection<Student> showStudentsByAge(@PathVariable Long age) {
        return studentServiceImpl.getStudentsByAge(age);
    }
}
