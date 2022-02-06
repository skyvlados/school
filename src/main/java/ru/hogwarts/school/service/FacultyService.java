package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyService {
    Faculty createFaculty(Faculty faculty);
    Faculty findFaculty(Long id);
    Faculty editFaculty(Long id, Faculty faculty);
    Faculty removeFaculty(Long id);
    Collection<Faculty> getFacultiesByColor(String color);
}