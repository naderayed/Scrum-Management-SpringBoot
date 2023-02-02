package com.nader.scrum.management.repositories;

import com.nader.scrum.management.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository not mandatory here because this interface extends JpaRepo andthe last contains @Repo Annotation
public interface AppUserRepo extends JpaRepository<AppUser,Long> {
}
