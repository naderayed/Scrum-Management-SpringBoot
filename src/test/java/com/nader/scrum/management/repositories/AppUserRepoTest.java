package com.nader.scrum.management.repositories;

import com.nader.scrum.management.entities.AppUser;
import com.nader.scrum.management.entities.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class AppUserRepoTest {

  @Autowired
  AppUserRepo underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckWhenUserExistsByEmail() {

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
                new ArrayList<>(),
                new ArrayList<>()

        );
        underTest.save(appUser);

        //When
        boolean exists = underTest.selectExistsEmail("user@email.com");

        //Then
        assertThat(exists).isTrue();

    }
    @Test
    void itShouldCheckWhenUserByEmailDoesNotExist() {



        //given
        AppUser appUser = new AppUser(
                (long) 100,
                "user@email.com",
                "1234",
                "user100",
                "last100",
                Role.DEVELOPER,
                new ArrayList<>(),
                new ArrayList<>()

        );

        //When
        boolean exists = underTest.selectExistsEmail("user@email.com");

        //Then
        assertThat(exists).isFalse();

    }

    @Test
    void itShouldSelectAppUserByEmail(){
        //given
        String emailUser = "user@email.com";
        AppUser appUser = new AppUser(
                (long) 100,
                emailUser,
                "1234",
                "user100",
                "last100",
                Role.DEVELOPER,
                new ArrayList<>(),
                new ArrayList<>()

        );
        underTest.save(appUser);
        //When
        Optional<AppUser> exists = underTest.findAppUserByEmailUser(emailUser);
        //Then
        assertThat(exists).isNotEmpty();
    }

    @Test
    void itShouldNotSelectAppUserByEmail(){
        //given
        String emailUser = "user@email.com";
        String falseEmail ="false@email.com";
        AppUser appUser = new AppUser(
                (long) 100,
                emailUser,
                "1234",
                "user100",
                "last100",
                Role.DEVELOPER,
                new ArrayList<>(),
                new ArrayList<>()

        );
        underTest.save(appUser);
        //When
        Optional<AppUser> exists = underTest.findAppUserByEmailUser(falseEmail);
        //Then
        assertThat(exists).isEmpty();
    }

    @Test
    void itShouldSelectAppUserByFirstnameAndLastname(){
        //Given
        String emailUser = "user@email.com";
        AppUser appUser = new AppUser(
                (long) 100,
                emailUser,
                "1234",
                "user100",
                "last100",
                Role.DEVELOPER,
                new ArrayList<>(),
                new ArrayList<>()
        );
        underTest.save(appUser);
        //When
        Optional<AppUser> exists = underTest.findByFirstnameAndLastname("user100", "last100");
        //Then
        assertThat(exists).isNotEmpty();

    }

    @Test
    void itShouldNotSelectAppUserByFirstnameAndLastname(){
        //Given
        String emailUser = "user@email.com";
        AppUser appUser = new AppUser(
                (long) 100,
                emailUser,
                "1234",
                "user100",
                "last100",
                Role.DEVELOPER,
                new ArrayList<>(),
                new ArrayList<>()
        );
        underTest.save(appUser);
        //When
        Optional<AppUser> exists = underTest.findByFirstnameAndLastname("falseFirst", "last100");
        //Then
        assertThat(exists).isEmpty();

    }

}