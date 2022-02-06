package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService{
    private final Map<Long, Faculty> facultyMap = new HashMap<>();

    @Override
    public Faculty createFaculty(Faculty faculty) {
        facultyMap.put(faculty.getId(), faculty);
        return faculty;
    }

    @Override
    public Faculty findFaculty(Long id) {
        if (!facultyMap.containsKey(id)) {
            throw new StudentNotFoundException();
        }
        return facultyMap.get(id);
    }

    @Override
    public Faculty editFaculty(Long id, Faculty faculty) {
        if (!facultyMap.containsKey(id)) {
            throw new StudentNotFoundException();
        }
        facultyMap.put(id, faculty);
        return faculty;
    }

    @Override
    public Faculty removeFaculty(Long id) {
        if (!facultyMap.containsKey(id)) {
            throw new StudentNotFoundException();
        }
        Faculty removeFaculty = facultyMap.get(id);
        facultyMap.remove(id);
        return removeFaculty;
    }

    @Override
    public Collection<Faculty> getFacultiesByColor(String color) {
        return facultyMap.values().stream()
                .filter(f-> Objects.equals(f.getColor(), color))
                .collect(Collectors.toList());
    }
}
