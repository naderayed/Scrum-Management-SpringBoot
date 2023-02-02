package com.nader.scrum.management.services;

import com.nader.scrum.management.entities.AppUser;
import com.nader.scrum.management.repositories.AppUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
//DI with Lombok features
public class AppUserService implements IAppUserService, ICrud<AppUser> {

    private final AppUserRepo appUserRepo;


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


}
