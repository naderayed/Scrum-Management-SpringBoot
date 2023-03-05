package com.nader.scrum.management.services;

import com.nader.scrum.management.dto.AppUserDTOMapper;
import com.nader.scrum.management.entities.AppUser;
import com.nader.scrum.management.entities.Role;
import com.nader.scrum.management.repositories.AppUserRepo;
import com.nader.scrum.management.repositories.ProjectRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.webjars.NotFoundException;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
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
    @Disabled
    void update() {
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
    @Disabled
    void delete() {
    }

}