package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private StudentService studentService;

    @Test
    public void contextLoad() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testPostStudent() throws Exception {
        Student testStudent = new Student();
        testStudent.setId(1L);
        testStudent.setName("testStudent");
        testStudent.setAge(20);

        Mockito.when(studentService.createStudent(Mockito.any())).thenReturn(testStudent);
        Assertions
                .assertThat(restTemplate.postForObject("http://localhost:" + port + "/students", testStudent,
                        Student.class))
                .isEqualTo(testStudent);
        Mockito.verify(studentService,Mockito.times(1))
                .createStudent(Mockito.eq(testStudent));
    }

    @Test
    public void testGetAllStudent() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/allStudent",
                                Collection.class))
                .isNotNull();
    }

    @Test
    public void testGetStudent() throws Exception {
        restTemplate.getForObject("http://localhost:" + port + "/students/"+1,
                Collection.class);
        Mockito.verify(studentService,Mockito.times(1))
                .findStudent(ArgumentMatchers.anyLong());
    }

    @Test
    public void testGetStudentByAge() throws Exception {
        restTemplate.getForObject("http://localhost:" + port + "/students/age/"+18,
                Collection.class);
        Mockito.verify(studentService,Mockito.times(1))
                .getStudentsByAge(ArgumentMatchers.anyInt());
    }

    @Test
    public void testGetStudentByAgeBetween() throws Exception {
        restTemplate.getForObject("http://localhost:" + port + "/students/minMaxStudents/"
                        +18+","+25,
                Collection.class);
        Mockito.verify(studentService,Mockito.times(1))
                .getStudentsByAgeBetween(ArgumentMatchers.anyInt(),ArgumentMatchers.anyInt());
    }

    @Test
    public void testDeleteStudentById() throws Exception {
                restTemplate.delete("http://localhost:" + port + "/students/"+1,
                        Collection.class);
                Mockito.verify(studentService,Mockito.times(1))
                        .removeStudent(ArgumentMatchers.anyLong());
    }

    @Test
    public void testPutStudent() throws Exception {
        Student testStudent = new Student();
        testStudent.setId(1L);
        testStudent.setName("testStudent");
        testStudent.setAge(20);
        restTemplate.put("http://localhost:" + port + "/students", testStudent,
                Collection.class);
        Mockito.verify(studentService,Mockito.times(1))
                .editStudent(ArgumentMatchers.anyLong(),Mockito.eq(testStudent));
    }

}
