package com.nader.scrum.management.repositories;

import com.nader.scrum.management.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//@Repository not mandatory here because this interface extends JpaRepo andthe last contains @Repo Annotation
public interface ProjectRepo extends JpaRepository<Project,Long> {
Optional<Project> findProjectByTitleProject(String titleProject);
}
