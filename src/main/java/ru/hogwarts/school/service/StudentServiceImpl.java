package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.StudentNotFoundException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.stream.Collectors;

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
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException();
        }
        return studentRepository.findById(id).get();

    }

    @Override
    public Student editStudent(Long id,Student student) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException();
        }
        return studentRepository.save(student);
    }

    @Override
    public Collection<Student> getStudentsByAge(Long age) {
        return studentRepository.findAll().stream()
                .filter(s->s.getAge()==age)
                .collect(Collectors.toList());
    }

    @Override
    public void removeStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException();
        }
        studentRepository.deleteById(id);
    }



}
