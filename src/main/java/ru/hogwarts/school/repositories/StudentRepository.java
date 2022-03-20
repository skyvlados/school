package ru.hogwarts.school.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findStudentByAge(int age);
    Collection<Student> findStudentByAgeBetween(int min, int max);
    @Query(value = "SELECT id FROM student ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Long findStudentLast();

}
