package com.nader.scrum.management.repositories;

import com.nader.scrum.management.entities.AppUser;
import com.nader.scrum.management.entities.Project;
import com.nader.scrum.management.entities.Role;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class AppUserRepoTest {

    private AppUserRepo appUserRepo;

    @Test
    void itShouldCheckIfUserExistsByEmail() {

        /*
            we will add H2 DataBase as inMemory DB just for test, to do that we need to add H2 Dependency
           then create a resources folder under test  finally create app.prop file to configure H2 DB
         */

        //given
        AppUser appUser = new AppUser(
                (long) 100,
                "user@email.com",
                "1234",
                "user100",
                "last100",
                Role.DEVELOPER,
                new ArrayList<Project>(),
                new ArrayList<Project>()
        );
        appUserRepo.save(appUser);

        //When
        boolean exists = appUserRepo.selectExistsEmail("user@email.com");

        //Then
        assertThat(exists).isTrue();

    }
}