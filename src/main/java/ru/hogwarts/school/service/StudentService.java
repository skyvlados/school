package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;
import java.util.Collection;

public interface StudentService {
    Student createStudent(Student student);
    void removeStudent(Long id);
    Student findStudent(Long id);
    Collection<Student> findAllStudent();
    Student editStudent(Long id, Student student);
    Collection<Student> getStudentsByAge(Long age);
    Collection<Student> findStudentName(String name);
    Collection<Student> findByNamePart(String name);
}
