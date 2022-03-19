package ru.hogwarts.school.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.LastFiveStudents;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findStudentByAge(int age);
    Collection<Student> findStudentByAgeBetween(int min, int max);

    @Query(value = "select count(*) from student", nativeQuery = true)
    Integer findCountStudent();

    @Query(value = "select avg(age) from student", nativeQuery = true)
    Integer findAvgAgeStudent();

    @Query(value = "select name from student order by id desc limit 5", nativeQuery = true)
    Collection<LastFiveStudents> findLastFiveStudent();


}
