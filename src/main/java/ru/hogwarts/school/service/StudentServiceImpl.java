package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.StudentNotFoundException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;
import java.util.Collection;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student findStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);

    }

    @Override
    public Student editStudent(Long id,Student student) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException();
        }
        return studentRepository.save(student);
    }

    @Override
    public Collection<Student> findStudentsByAge(int age) {
        return studentRepository.findStudentByAge(age);
    }

    @Override
    public Collection<Student> findStudentsByAgeBetween(int min, int max) {
        return studentRepository.findStudentByAgeBetween(min,max);
    }

    @Override
    public void removeStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException();
        }
        studentRepository.deleteById(id);
    }

    @Override
    public Collection<Student> getAllStudent() {
        return studentRepository.findAll();
    }

}
