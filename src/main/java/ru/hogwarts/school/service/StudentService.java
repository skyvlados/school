package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;
import java.util.Collection;

public interface StudentService {
    Student createStudent(Student student);
    void removeStudent(Long id);
    Student findStudent(Long id);
    Student editStudent(Long id, Student student);
    Collection<Student> getStudentsByAge(int age);
    Collection<Student> getStudentsByAgeBetween(int min, int max);
}
