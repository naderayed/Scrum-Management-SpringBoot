package com.nader.scrum.management.controllers;


import com.nader.scrum.management.entities.Sprint;
import com.nader.scrum.management.services.interfaces.ICrud;
import com.nader.scrum.management.services.interfaces.ISprintServcie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SprintController {
    private final ICrud<Sprint> sprintICrud;
    private final ISprintServcie iSprintServcie;

    @PostMapping("addSprintAndAssignToProject")
    public ResponseEntity<String> addSprintAndAssignToProject(@RequestBody Sprint sprint, @RequestParam int idProject){
        iSprintServcie.addSprintAndAssignToProject(sprint,idProject);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }
    //TODO CRUD
}
