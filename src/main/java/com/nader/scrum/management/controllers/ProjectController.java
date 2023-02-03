package com.nader.scrum.management.controllers;


import com.nader.scrum.management.entities.Project;
import com.nader.scrum.management.services.ICrud;
import com.nader.scrum.management.services.IProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectController {
    private final ICrud<Project> projectICrud;
    private final IProjectService iProjectService;


    @PostMapping("addProject")
    public Project addProject(@RequestBody Project project) {
        return projectICrud.create(project);
    }

    @GetMapping("getProject")
    public Project getProject(@RequestParam Long id) {
        return projectICrud.get(id);
    }

    @PutMapping("updateProject")
    public Project updateProject(@RequestBody Project project) {
        return projectICrud.update(project);
    }

    @DeleteMapping("deleteProject")
    public void deleteProject(@RequestParam Long id) {
        projectICrud.delete(id);
    }

    @GetMapping("getAllProject")
    public List<Project> getAllProjects() {
        return iProjectService.getAllProjects();
    }
}
