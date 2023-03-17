package com.nader.scrum.management.dto.mappers;

import com.nader.scrum.management.dto.ProjectDTO;
import com.nader.scrum.management.entities.Project;

import java.util.function.Function;

public class ProjectDTOMapper implements Function<Project, ProjectDTO>{
    @Override
    public ProjectDTO apply(Project project) {
        return ProjectDTO.builder()
                .idProject(project.getIdProject())
                .titleProject(project.getTitleProject())
                .projectDescription(project.getProjectDescription())
                .appUsers(project.getAppUsers())
                .sprints(project.getSprints())
                .build();
    }
}
