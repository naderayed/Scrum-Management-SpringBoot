package com.nader.scrum.management.controllers;


import com.nader.scrum.management.dto.ProjectDTO;
import com.nader.scrum.management.entities.Project;
import com.nader.scrum.management.services.interfaces.ICrud;
import com.nader.scrum.management.services.interfaces.IProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectController {
    private final ICrud<Project> projectICrud;
    private final IProjectService iProjectService;


    @PostMapping("addProject")
    public ResponseEntity<Project> addProject(@RequestBody Project project) {
        return ResponseEntity.accepted().body(projectICrud.create(project));
    }

    @GetMapping("getProject")
    public ResponseEntity<Project> getProject(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body( projectICrud.get(id));
    }

    @PutMapping("updateProject")
    public ResponseEntity<Project> updateProject(@RequestBody Project project) {
        return ResponseEntity.ok().body(projectICrud.update(project));
    }

    @DeleteMapping("deleteProject")
    public ResponseEntity<String> deleteProject(@RequestParam Long id) {
        projectICrud.delete(id);
        return  ResponseEntity.accepted().body("Deleted successfully");
    }

    @GetMapping("getAllProject")
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        return ResponseEntity.
                status(HttpStatus.FOUND)
                .body(iProjectService.getAllProjects());
    }

    @GetMapping("getProjectsByScrumMaster")
    public ResponseEntity<List<ProjectDTO>> getProjectsByScrumMaster(@RequestParam String fName, String lName) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(iProjectService.getProjectsByScrumMaster(fName, lName));
    }
}
