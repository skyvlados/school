package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.StudentNotFoundException;
import ru.hogwarts.school.model.Student;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final Map<Long, Student> studentMap = new HashMap<>();

    @Override
    public Student createStudent(Student student) {
        studentMap.put(student.getId(), student);
        return student;
    }

    @Override
    public Student findStudent(Long id) {
        if (!studentMap.containsKey(id)) {
            throw new StudentNotFoundException();
        }
        return studentMap.get(id);

    }

    @Override
    public Student editStudent(Long id,Student student) {
        if (!studentMap.containsKey(id)) {
            throw new StudentNotFoundException();
        }
        studentMap.put(id, student);
        return student;
    }

    @Override
    public Collection<Student> getStudentsByAge(Long age) {
        return studentMap.values().stream()
                .filter(s->s.getAge()==age)
                .collect(Collectors.toList());
    }

    @Override
    public Student removeStudent(Long id) {
        if (!studentMap.containsKey(id)) {
            throw new StudentNotFoundException();
        }
        return studentMap.remove(id);
    }



}
