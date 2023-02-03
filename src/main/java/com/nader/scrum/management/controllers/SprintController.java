package com.nader.scrum.management.controllers;


import com.nader.scrum.management.entities.Sprint;
import com.nader.scrum.management.services.ICrud;
import com.nader.scrum.management.services.ISprintServcie;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SprintController {
    private final ICrud<Sprint> sprintICrud;
    private final ISprintServcie iSprintServcie;

    //TODO CRUD
}
