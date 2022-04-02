package ru.hogwarts.school;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class FacultyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService facultyService;

    @MockBean
    private StudentService studentService;

    @MockBean
    private AvatarService avatarService;

    @InjectMocks
    private FacultyController facultyController;

    @Test
    public void saveFacultyTest() throws Exception {
        final Long id = 1L;
        final String name = "new Faculty";
        final String color = "blue";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        JSONObject facultyObjectForEdit = new JSONObject();
        facultyObjectForEdit.put("id",id);
        facultyObjectForEdit.put("name", name);
        facultyObjectForEdit.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyService.createFaculty(any(Faculty.class))).thenReturn(faculty);
        when(facultyService.findFaculty(any(Long.class))).thenReturn(faculty);
        when(facultyService.editFaculty(any(Long.class),any(Faculty.class))).thenReturn(faculty);
        when(facultyService.getFacultiesByColor(any(String.class))).thenReturn(Collections.singleton(faculty));
        when(facultyService.getFacultiesByColorOrName(ArgumentMatchers.eq(color), ArgumentMatchers.eq("")))
                .thenReturn(Collections.singleton(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculties")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculties/"+id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculties")
                        .content(facultyObjectForEdit.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculties/color/"+color)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$[0].id").value(id))
                .andExpect(jsonPath("$[0].name").value(name))
                .andExpect(jsonPath("$[0].color").value(color));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculties/"+id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); //receive
    }
}