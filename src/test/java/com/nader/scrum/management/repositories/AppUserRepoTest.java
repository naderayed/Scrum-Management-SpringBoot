package com.nader.scrum.management.repositories;

import com.nader.scrum.management.entities.AppUser;
import com.nader.scrum.management.entities.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
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

        //Arrange

        AppUser appuser = AppUser.builder()
                .emailUser("user@email.com")
                .password("1234")
                .firstname("user100")
                .lastname("last100")
                .role(Role.DEVELOPER)
                .developersProjects(new ArrayList<>())
                .scrumProjects(new ArrayList<>()).build();


        //Act
        AppUser savedUser = underTest.save(appuser);
        boolean exists = underTest.selectExistsEmail("user@email.com");

        //Assert
        assertThat(exists).isTrue();
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getIdUser()).isPositive();

    }
    @Test
    void itShouldCheckWhenUserByEmailDoesNotExist() {

        //Arrange
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

        //Act
        boolean exists = underTest.selectExistsEmail("user@email.com");

        //Assert
        assertThat(exists).isFalse();

    }

    @Test
    void itShouldSelectAppUserByEmail(){
        //Arrange
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
        //Act
        Optional<AppUser> exists = underTest.findAppUserByEmailUser(emailUser);
        //Assert
        assertThat(exists).isNotEmpty();
    }

    @Test
    void itShouldNotSelectAppUserByEmail(){
        //Arrange
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
        //Act
        Optional<AppUser> exists = underTest.findAppUserByEmailUser(falseEmail);
        //Assert
        assertThat(exists).isEmpty();
    }

    @Test
    void itShouldSelectAppUserByFirstnameAndLastname(){
        //Arrange
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
        //Act
        Optional<AppUser> exists = underTest.findByFirstnameAndLastname("user100", "last100");
        //Assert
        assertThat(exists).isNotEmpty();

    }

    @Test
    void itShouldNotSelectAppUserByFirstnameAndLastname(){
        //Arrange
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
        //Act
        Optional<AppUser> exists = underTest.findByFirstnameAndLastname("falseFirst", "last100");
        //Assert
        assertThat(exists).isEmpty();

    }

}