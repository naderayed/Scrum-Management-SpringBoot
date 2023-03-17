package com.nader.scrum.management.dto.mappers;

import com.nader.scrum.management.dto.ProjectDTO;
import com.nader.scrum.management.entities.Project;
import org.springframework.stereotype.Component;

import java.util.function.Function;


@Component
public class ProjectDTOMapper implements Function<Project, ProjectDTO>{
    @Override
    public ProjectDTO apply(Project project) {
        return  new ProjectDTO(project.getIdProject()
                ,project.getTitleProject(),
                project.getProjectDescription(),
                project.getAppUsers(),
                project.getSprints());

    }
}
