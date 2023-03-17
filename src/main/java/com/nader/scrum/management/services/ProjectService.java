package com.nader.scrum.management.services;

import com.nader.scrum.management.dto.ProjectDTO;
import com.nader.scrum.management.dto.mappers.ProjectDTOMapper;
import com.nader.scrum.management.entities.AppUser;
import com.nader.scrum.management.entities.Project;
import com.nader.scrum.management.entities.Role;
import com.nader.scrum.management.repositories.AppUserRepo;
import com.nader.scrum.management.repositories.ProjectRepo;
import com.nader.scrum.management.services.interfaces.ICrud;
import com.nader.scrum.management.services.interfaces.IProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service

@Slf4j
public class ProjectService implements IProjectService, ICrud<Project> {

    private final ProjectRepo projectRepo;
    private final AppUserRepo appUserRepo;

    private final ProjectDTOMapper projectDTOMapper;

    public ProjectService(ProjectRepo projectRepo, AppUserRepo appUserRepo, ProjectDTOMapper projectDTOMapper) {
        this.projectRepo = projectRepo;
        this.appUserRepo = appUserRepo;
        this.projectDTOMapper = projectDTOMapper;
    }

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
            return this.create(project);
        return null;
    }

    @Override
    public void delete(Long id) {
        projectRepo.deleteById(id);
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = projectRepo.findAll();
     return  projects.stream()
             .map(projectDTOMapper).collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> getProjectsByScrumMaster(String fName, String lName) {
        AppUser appUser = appUserRepo.findByFirstnameAndLastname(fName, lName)
                .orElseThrow(() -> new RuntimeException("No User found named " + fName + " " + lName));
        if (appUser.getRole() == Role.SCRUM_MASTER)
            return appUser.getScrumProjects()
                    .stream().map(projectDTOMapper)
                    .collect(Collectors.toList());
        return Collections.emptyList();
    }

    //TODO uncomment
   // @Scheduled(fixedRate =100000)
    public void getProjectsCurrentSprints() {
        List<Project> allProjects = projectRepo.findAll();
        allProjects.forEach(project -> project.getSprints().forEach(sprint -> {
            if (sprint.getStartDate().before(new Date()))
                log.info(project.toString());
        }));
    }
}
