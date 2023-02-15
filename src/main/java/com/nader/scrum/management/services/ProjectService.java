package com.nader.scrum.management.services;

import com.nader.scrum.management.dto.AppUserDTO;
import com.nader.scrum.management.entities.AppUser;
import com.nader.scrum.management.entities.Project;
import com.nader.scrum.management.entities.Role;
import com.nader.scrum.management.repositories.AppUserRepo;
import com.nader.scrum.management.repositories.ProjectRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService implements IProjectService, ICrud<Project> {

    private final ProjectRepo projectRepo;
    private final AppUserRepo appUserRepo;

    @Override
    public Project create(Project project) {
        if (project != null)
            return projectRepo.save(project);
        return null;
    }

    @Override
    public Project get(Long id) {
        return projectRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No project with ID: " + id));
    }

    @Override
    public Project update(Project project) {
        if (project != null)
            return projectRepo.save(project);
        return null;
    }

    @Override
    public void delete(Long id) {
        projectRepo.deleteById(id);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }

    @Override
    public List<Project> getProjectsByScrumMaster(String fName, String lName) {
        AppUser appUser = appUserRepo.findByFirstnameAndLastname(fName, lName)
                .orElseThrow(() -> new RuntimeException("No User found named " + fName + " " + lName));
        if (appUser.getRole() == Role.SCRUM_MASTER)
            return appUser.getScrumProjects();
        return Collections.emptyList();
    }

    //TODO uncomment
   // @Scheduled(fixedRate =100000)
    public void getProjectsCurrentSprints() {
        List<Project> allProjects = this.getAllProjects();
        allProjects.forEach(project -> project.getSprints().forEach(sprint -> {
            if (sprint.getStartDate().before(new Date()))
                log.info(project.toString());
        }));
    }
}
