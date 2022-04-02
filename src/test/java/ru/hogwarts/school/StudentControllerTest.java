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
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.StudentService;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.Collection;
import java.util.regex.Matcher;

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

    @MockBean
    private StudentService studentService;

    @Test
    public void contextLoad() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testPostStudent() throws Exception {
        long studentLastId=1L;
        String nameStudent="testStudent";
        int ageStudent=20;
        Student testStudent = new Student();
       testStudent.setId(studentLastId+1);
        testStudent.setName(nameStudent);
        testStudent.setAge(ageStudent);

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
        Collection<Student> testStudent= studentService.getAllStudent();
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/allStudent",
                                Collection.class))
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

    @Test
    public void testGetStudentByAge() throws Exception {
        Long studentLastId=studentRepository.findStudentLast();
        Student testStudent= studentService.findStudent(studentLastId);
        int studentAge=testStudent.getAge();
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/age/"+studentAge,
                        Collection.class))
                .isNotNull();
    }
    @Test
    public void testGetStudentByAgeBetween() throws Exception {
        Long studentLastId=studentRepository.findStudentLast();
        Student testStudent= studentService.findStudent(studentLastId);
        int studentAge1=testStudent.getAge();
        int studentAge2=testStudent.getAge()+10;
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/students/minMaxStudents/"
                                +studentAge1+","+studentAge2,
                        Collection.class))
                .isNotNull();
    }
    @Test
    public void testDeleteStudentById() throws Exception {
                restTemplate.delete("http://localhost:" + port + "/students/"+1,
                        Collection.class);
                Mockito.verify(studentService,Mockito.times(1))
                        .removeStudent(ArgumentMatchers.anyLong());
    }

//    @Test
//    public void testPutStudent() throws Exception {
//        Long studentLastId=studentRepository.findStudentLast();
//        String nameStudent="testStudent";
//        int ageStudent=20;
//        Student testStudent = new Student();
//        testStudent.setId(studentLastId);
//        testStudent.setName(nameStudent);
//        testStudent.setAge(ageStudent);
//
//        Assertions
//                .assertThat(this.restTemplate.put("http://localhost:" + port + "/students", testStudent,
//                        Student.class);)
//                .isEqualTo(testStudent);
//
//    }

}
