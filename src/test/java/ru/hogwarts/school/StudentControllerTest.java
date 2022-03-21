package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.StudentService;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.Collection;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @LocalServerPort
    private int port;


    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @Test
    public void contextLoad() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testPostStudent() throws Exception {
        Long studentLastId=studentRepository.findStudentLast();
        String nameStudent="testStudent";
        int ageStudent=20;
        Student testStudent = new Student();
        testStudent.setId(studentLastId+1);
        testStudent.setName(nameStudent);
        testStudent.setAge(ageStudent);

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/students", testStudent,
                        Student.class))
                .isEqualTo(testStudent);

    }

    @Test
    public void testGetAllStudent() throws Exception {
        Collection<Student> testStudent= studentService.getAllStudent();
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/allStudent",
                                Collection.class))
                .isEqualTo(testStudent)
                .isNotNull();
    }

    @Test
    public void testGetStudent() throws Exception {
        Long studentLastId=studentRepository.findStudentLast();
        Student testStudent= studentService.findStudent(studentLastId);
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/"+studentLastId,
                        Student.class))
                .isEqualTo(testStudent);
    }

}
