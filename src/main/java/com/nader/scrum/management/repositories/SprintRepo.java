package com.nader.scrum.management.repositories;

import com.nader.scrum.management.entities.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//@Repository not mandatory here because this interface extends JpaRepo andthe last contains @Repo Annotation
public interface SprintRepo extends JpaRepository<Sprint,Long> {
}
