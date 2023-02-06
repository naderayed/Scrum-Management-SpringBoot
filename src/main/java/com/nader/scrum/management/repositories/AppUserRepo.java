package com.nader.scrum.management.repositories;

import com.nader.scrum.management.entities.AppUser;
import com.nader.scrum.management.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//@Repository not mandatory here because this interface extends JpaRepo and the last contains @Repo Annotation
public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByFirstnameAndLastname(String firstname, String lastname);



}
