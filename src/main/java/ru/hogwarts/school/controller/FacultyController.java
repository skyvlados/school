package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("/faculties")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public Faculty getFaculty(@RequestParam Long id) {
        return facultyService.findFaculty(id);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping
    public Faculty editStudent(@RequestBody Faculty faculty) {
        return facultyService.editFaculty(faculty.getId(),faculty);
    }

    @DeleteMapping("{id}")
    public Faculty removeStudent(@PathVariable Long id) {
        return facultyService.removeFaculty(id);
    }

    @GetMapping("{color}")
    public Collection<Faculty> showStudentsByAge(@PathVariable String color) {
        return facultyService.getFacultiesByColor(color);
    }

}
