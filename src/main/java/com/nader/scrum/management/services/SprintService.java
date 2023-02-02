package com.nader.scrum.management.services;


import com.nader.scrum.management.entities.Sprint;
import com.nader.scrum.management.repositories.SprintRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SprintService implements ISprintServcie,ICrud<Sprint> {

    private final SprintRepo sprintRepo;

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
}
