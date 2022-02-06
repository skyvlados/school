package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("/faculties")
public class FacultyController {
    private final FacultyServiceImpl FacultyServiceImpl;

    public FacultyController(FacultyServiceImpl FacultyServiceImpl) {
        this.FacultyServiceImpl = FacultyServiceImpl;
    }

    @GetMapping()
    public Faculty getFaculty(@RequestParam Long id) {
        return FacultyServiceImpl.findFaculty(id);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return FacultyServiceImpl.createFaculty(faculty);
    }

    @PutMapping
    public Faculty editStudent(@RequestBody Faculty faculty) {
        return FacultyServiceImpl.editFaculty(faculty.getId(),faculty);
    }

    @DeleteMapping("{id}")
    public Faculty removeStudent(@PathVariable Long id) {
        return FacultyServiceImpl.removeFaculty(id);
    }

    @GetMapping("{color}")
    public Collection<Faculty> showStudentsByAge(@PathVariable String color) {
        return FacultyServiceImpl.getFacultiesByColor(color);
    }

}
