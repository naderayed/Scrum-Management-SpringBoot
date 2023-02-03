package com.nader.scrum.management.services;

import com.nader.scrum.management.entities.AppUser;
import com.nader.scrum.management.entities.Project;
import com.nader.scrum.management.entities.Role;
import com.nader.scrum.management.repositories.AppUserRepo;
import com.nader.scrum.management.repositories.ProjectRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
//DI with Lombok features
public class AppUserService implements IAppUserService, ICrud<AppUser> {

    private final AppUserRepo appUserRepo;
    private final ProjectRepo projectRepo;


    @Override
    public AppUser create(AppUser appUser) {
        if (appUser != null)
            return appUserRepo.save(appUser);
        return null;
    }

    @Override
    public AppUser get(Long id) {
        return appUserRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException("No User With ID :" + id));

    }

    @Override
    public AppUser update(AppUser appUser) {
        if (appUser != null)
            return appUserRepo.save(appUser);
        return null;
    }

    @Override
    public void delete(Long id) {
        appUserRepo.deleteById(id);
    }


    @Override
    public List<AppUser> getAllUsers() {
        return appUserRepo.findAll();
    }

    @Override
    public void assignProjectToDeveloper(int projectId, int devId) {
        Project project = projectRepo.findById((long) projectId)
                .orElseThrow(() -> new RuntimeException("No Project with ID: " + projectId));
        AppUser appUser = appUserRepo.findById((long) devId)
                .orElseThrow(() -> new RuntimeException("No Developer with ID: " + devId));
        if (appUser.getRole() == Role.DEVELOPER) {
            appUser.getDevelopersProjects().add(project);
            appUserRepo.save(appUser);
        } else
            throw new RuntimeException("User Must be Developer Role");

    }
}
