package com.nader.scrum.management.services;

import com.nader.scrum.management.dto.ProjectDTO;
import com.nader.scrum.management.dto.mappers.ProjectDTOMapper;
import com.nader.scrum.management.entities.Project;
import com.nader.scrum.management.repositories.AppUserRepo;
import com.nader.scrum.management.repositories.ProjectRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

//ExtendsWith annotation replace all creation and closing of Mockito
class ProjectServiceTest {


    @Mock
    private ProjectRepo projectRepo;
    @Mock
    private AppUserRepo appUserRepo;

    @Mock
    private ProjectDTOMapper projectDTOMapper;
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
        Project testProject = new Project(
                1L,
                "proTitle",
                "description",
                new ArrayList<>(),
                new ArrayList<>());
        //Act
        when(projectRepo.save(Mockito.any(Project.class))).thenReturn(testProject);
        Project resultProject = underTest.create(testProject);
        //assert
        assertThat(resultProject).isEqualTo(testProject);

    }

    @Test
    void itShouldReturnsAllProjects() {
        //Arrange

        Project project = Mockito.mock(Project.class);
        Project project1 = Mockito.mock(Project.class);
        when(projectRepo.findAll()).thenReturn(List.of(project, project1));
        //Act
        List<ProjectDTO> allProjects = underTest.getAllProjects();
        //Assert
        assertThat(allProjects).isNotNull()
                .hasSize(2);
    }


    @Test
    void itShouldReturnsProjectMatchingId() {

        //Arrange
        Project testProject = new Project(
                1L,
                "proTitle",
                "description",
                new ArrayList<>(),
                new ArrayList<>());
        when(projectRepo.findById(1L)).thenReturn(Optional.of(testProject));
        //Act
        Project result = underTest.get(1L);
        //Assert
        assertThat(result).isNotNull().isEqualTo(testProject);
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