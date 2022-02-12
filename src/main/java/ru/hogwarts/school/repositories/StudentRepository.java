package ru.hogwarts.school.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findStudentByAge(int age);
    Collection<Student> findStudentsByName(String name);
    Collection<Student> findStudentsByNameContains(String name);
    Collection<Student> findStudentsByAgeBetween(int age1,int age2);
}
