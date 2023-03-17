package com.nader.scrum.management.services;

import com.nader.scrum.management.entities.Project;
import com.nader.scrum.management.repositories.AppUserRepo;
import com.nader.scrum.management.repositories.ProjectRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)

//ExtendsWith annotation replace all creation and closing of Mockito
class ProjectServiceTest {


    @Mock
    private ProjectRepo projectRepo;
    @Mock
    private AppUserRepo appUserRepo;
    //TODelete if we are using ExtendsWith
    //private AutoCloseable autoCloseable;
  @InjectMocks
    private ProjectService underTest;

    @BeforeEach
    void setUp() {
        Project project = new Project(
                0L,
                "alreadyInMem",
                "alreadyInMem",
                new ArrayList<>(),
                new ArrayList<>());
        underTest.create(project);
        //TODelete if we are using ExtendsWith
       // autoCloseable = MockitoAnnotations.openMocks(this);
    }

    //TODelete if we are using ExtendsWith
//    @AfterEach
//    void tearDown() throws Exception {
//        autoCloseable.close();
//
//    }


    @Test
    void canAddNewProject() {
        //Arrange
        Project project = new Project(
                1L,
                "proTitle",
                "description",
                new ArrayList<>(),
                new ArrayList<>());
        //Act
        underTest.create(project);
        ArgumentCaptor<Project> projectArgumentCaptor = ArgumentCaptor.forClass(Project.class);
        verify(projectRepo).save(projectArgumentCaptor.capture());
        Project value = projectArgumentCaptor.getValue();
        //then
        assertThat(value).isEqualTo(project);


    }

    @Test
    void itShouldReturnsAllProjects() {
        //when
        underTest.getAllProjects();
        //then
        verify(projectRepo).findAll();
    }

    @Test
    void itShouldReturnsProjectById() {
        //given
        Long projectID = 0L;
        //when
        underTest.get(projectID);
        //then
        verify(projectRepo).findById(projectID);
    }

    @Test
    @Disabled
    void update() {
    }

    @Test
    @Disabled
    void delete() {
    }

    @Test
    @Disabled
    void getAllProjects() {
    }

    @Test
    @Disabled
    void getProjectsByScrumMaster() {
    }

    @Test
    @Disabled
    void getProjectsCurrentSprints() {
    }
}