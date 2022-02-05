package ru.hogwarts.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.hogwarts.school.data.Faculty;
import ru.hogwarts.school.data.Student;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SchoolApplication {

    public static void main(String[] args) {
        Student garry=new Student(1L,"Garry Potter", 11);
        Student malfoy = new Student(2L, "Malfoy", 12);
        Student pufik = new Student(3L, "Pufik", 13);
        Faculty griffindor = new Faculty(1L, "Griffindor", "orange");
        Faculty slizerin=new Faculty(2L,"Slizerin","green");
        Faculty puffendyi = new Faculty(3L, "Puffendyi", "blue");
        List<Student> arrayListStudent=new ArrayList<>();
        List<Faculty> arrayListFaculty=new ArrayList<>();
        arrayListStudent.add(garry);
        arrayListStudent.add(malfoy);
        arrayListStudent.add(pufik);
        arrayListFaculty.add(griffindor);
        arrayListFaculty.add(slizerin);
        arrayListFaculty.add(puffendyi);
        System.out.println(arrayListStudent);
        System.out.println(arrayListFaculty);
        SpringApplication.run(SchoolApplication.class, args);
    }

}
