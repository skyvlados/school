package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyServiceImpl implements FacultyService{
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findFaculty(Long id) {
        return facultyRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    @Override
    public Faculty editFaculty(Long id, Faculty faculty) {
        if (!facultyRepository.existsById(id)) {
            throw new StudentNotFoundException();
        }
        return facultyRepository.save(faculty);
    }

    @Override
    public void removeFaculty(Long id) {
        if (!facultyRepository.existsById(id)) {
            throw new StudentNotFoundException();
        }
        facultyRepository.deleteById(id);
    }

    @Override
    public Collection<Faculty> getFacultiesByColor(String color) {
        return facultyRepository.findFacultiesByColorIsIgnoreCase(color);
    }

    @Override
    public Collection<Faculty> getFacultiesByColorOrName(String color, String name) {
        if (color == null && name == null) {
            throw new StudentNotFoundException();
        } else if(color != null){
        return facultyRepository.findFacultiesByColorIsIgnoreCase(color);
        }
        else if(name != null){
            return facultyRepository.findFacultiesByNameIsIgnoreCase(name);
        }
        return facultyRepository.findFacultiesByColorOrName(color,name);
    }
}
