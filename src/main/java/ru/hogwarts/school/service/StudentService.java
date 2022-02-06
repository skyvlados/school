package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentService {
    Student createStudent(Student student);
    Student removeStudent(Long id);
    Student findStudent(Long id);
    Student editStudent(Long id,Student student);
    Collection<Student> getStudentsByAge(Long age);
}