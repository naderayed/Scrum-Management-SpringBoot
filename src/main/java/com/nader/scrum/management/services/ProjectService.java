package com.nader.scrum.management.services;

import com.nader.scrum.management.entities.Project;
import com.nader.scrum.management.repositories.ProjectRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService implements IProjectService,ICrud<Project>{

    private final ProjectRepo projectRepo;

    @Override
    public Project create(Project project) {
        if(project != null)
            return projectRepo.save(project);
        return null;
    }

    @Override
    public Project get(Long id) {
        return projectRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No project with ID: "+id));
    }

    @Override
    public Project update(Project project) {
        if(project != null)
            return projectRepo.save(project);
        return null;
    }

    @Override
    public void delete(Long id) {
        projectRepo.deleteById(id);

    }
}
