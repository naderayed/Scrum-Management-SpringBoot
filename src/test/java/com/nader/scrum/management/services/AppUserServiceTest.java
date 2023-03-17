package com.nader.scrum.management.services;

import com.nader.scrum.management.dto.AppUserDTOMapper;
import com.nader.scrum.management.entities.AppUser;
import com.nader.scrum.management.entities.Role;
import com.nader.scrum.management.repositories.AppUserRepo;
import com.nader.scrum.management.repositories.ProjectRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.webjars.NotFoundException;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class AppUserServiceTest {


    @Mock
    private AppUserRepo appUserRepo;
    //   private ll autoCloseable;
    @Mock
    private ProjectRepo projectRepo;
    @Mock
    private AppUserDTOMapper appUserDTOMapper;

    private AppUserService underTest;

    @BeforeEach
    void setUp() {
        //   autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new AppUserService(appUserRepo, projectRepo, appUserDTOMapper);

    }

//    @AfterEach
//    void tearDown() throws Exception {
//        autoCloseable.close();
//    }

    @Test
    void canGetAllAppUser() {

        //When
        underTest.getAllUsers();
        //Then
        verify(appUserRepo).findAll();
    }


    @Test
    void canAddAppUser() {
        //given
        AppUser appUser = new AppUser(
                (long) 100,
                "email@email.com",
                "1234",
                "user100",
                "last100",
                Role.DEVELOPER,
                new ArrayList<>(),
                new ArrayList<>()
        );

        //when
        underTest.create(appUser);

        //then

        ArgumentCaptor<AppUser> appUserArgumentCaptor = ArgumentCaptor.forClass(AppUser.class);

        verify(appUserRepo).save(appUserArgumentCaptor.capture());
        AppUser captorUser = appUserArgumentCaptor.getValue();
        assertThat(captorUser).isEqualTo(appUser);
    }

    @Test
    void itSouldUpdateAppUser() {
        //given
        AppUser appUser = new AppUser(
                (long) 100,
                "email@email.com",
                "1234",
                "user100",
                "last100",
                Role.DEVELOPER,
                new ArrayList<>(),
                new ArrayList<>()
        );
        AppUser toUpdateUser = new AppUser(
                (long) 100,
                "emai99l@email.com",
                "1234",
                "user99",
                "last99",
                Role.DEVELOPER,
                new ArrayList<>(),
                new ArrayList<>()
        );
        underTest.update(toUpdateUser);
        //when

        verify(appUserRepo).save(toUpdateUser);
        //then
    }

    @Test
    void willThrowWhenEmailGiven() {
        //given
        AppUser appUser = new AppUser(
                (long) 100,
                "email@email.com",
                "1234",
                "user100",
                "last100",
                Role.DEVELOPER,
                new ArrayList<>(),
                new ArrayList<>()
        );
        given(appUserRepo.selectExistsEmail(appUser.getEmailUser())).willReturn(true);

        //when
        //then

        assertThatThrownBy(() -> underTest.create(appUser))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Email is taken, try another Email!");
    }

    @Test
    void itShouldThrowsWhenDeleteAppUserNoFound() {
        //given
        AppUser appUser = new AppUser(
                (long) 100,
                "email@email.com",
                "1234",
                "user100",
                "last100",
                Role.DEVELOPER,
                new ArrayList<>(),
                new ArrayList<>()
        );

        //when
        //then
        assertThatThrownBy(() -> underTest.delete(appUser.getIdUser()))
                .hasMessageContaining("No User With ID " + appUser.getIdUser())
                .isInstanceOf(NotFoundException.class);
    }


}