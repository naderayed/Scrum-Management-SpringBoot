package com.nader.scrum.management.services;

import com.nader.scrum.management.dto.AppUserDTOMapper;
import com.nader.scrum.management.repositories.AppUserRepo;
import com.nader.scrum.management.repositories.ProjectRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AppUserServiceTest {


    @Mock
    private AppUserRepo appUserRepo;
 //   private AutoCloseable autoCloseable;
    @Mock
    private ProjectRepo projectRepo;
    @Mock
    private AppUserDTOMapper appUserDTOMapper ;

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
    @Disabled
    void delete() {
    }

}