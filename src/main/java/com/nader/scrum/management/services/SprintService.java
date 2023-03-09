package com.nader.scrum.management.services;


import com.nader.scrum.management.entities.Project;
import com.nader.scrum.management.entities.Sprint;
import com.nader.scrum.management.repositories.ProjectRepo;
import com.nader.scrum.management.repositories.SprintRepo;
import com.nader.scrum.management.services.interfaces.ICrud;
import com.nader.scrum.management.services.interfaces.ISprintServcie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SprintService implements ISprintServcie, ICrud<Sprint> {

    private final SprintRepo sprintRepo;
    private final ProjectRepo projectRepo;

    @Override
    public Sprint create(Sprint sprint) {
        if(sprint != null)
            return sprintRepo.save(sprint);
        return null;
    }

    @Override
    public Sprint get(Long id) {
        return sprintRepo.findById(id).orElseThrow(() -> new RuntimeException("no sprint with ID : "+id));
    }

    @Override
    public Sprint update(Sprint sprint) {
        if(sprint != null)
            return sprintRepo.save(sprint);
        return null;
    }

    @Override
    public void delete(Long id) {
        sprintRepo.deleteById(id);

    }

    @Override
    public void addSprintAndAssignToProject(Sprint sprint, int idProject) {
        Project project = projectRepo.findById((long) idProject)
                .orElseThrow(() -> new RuntimeException("No Project found With Id " + idProject));
        sprint.setProject(project);
        sprintRepo.save(sprint);
    }
}
