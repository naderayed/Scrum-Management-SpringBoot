package com.nader.scrum.management.repositories;

import com.nader.scrum.management.entities.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ProjectRepoTest {

    @Autowired
    private ProjectRepo underTest;

    @Test
    void itShouldReturnsProjectByTitleProject() {
        //Arrange
        Project project = Project.builder()
                .titleProject("PIDEV")
                .projectDescription("academic Project")
                .appUsers(new ArrayList<>())
                .sprints(new ArrayList<>())
                .build();
        underTest.save(project);
        //Act
        Project pidev = underTest.findProjectByTitleProject("PIDEV").get();
        //Assert
        assertThat(pidev).isNotNull();
    }

    @Test
    void itShouldUpdateProject() {
        //Arrange
        Project project = Project.builder()
                .titleProject("PIDEV")
                .projectDescription("academic Project")
                .appUsers(new ArrayList<>())
                .sprints(new ArrayList<>())
                .build();
        underTest.save(project);
        Project pidev = underTest.findById(project.getIdProject()).get();
        //Act
        pidev.setTitleProject("ReSullpy");
        underTest.save(pidev);

        //Assert
        assertThat(pidev).isNotNull();
        assertThat(pidev.getIdProject()).isEqualTo(project.getIdProject());
        assertThat(pidev.getTitleProject()).isNotNull();
    }

    @Test
    void itShouldDeleteProject() {
        //Arrange
        Project project = Project.builder()
                .titleProject("PIDEV")
                .projectDescription("academic Project")
                .appUsers(new ArrayList<>())
                .sprints(new ArrayList<>())
                .build();
        underTest.save(project);

        //Act

        underTest.delete(project);
        Optional<Project> project1 = underTest.findById(project.getIdProject());

        //Assert
        assertThat(project1).isEmpty();

    }
}
