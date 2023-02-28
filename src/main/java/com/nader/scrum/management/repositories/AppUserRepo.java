package com.nader.scrum.management.repositories;

import com.nader.scrum.management.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

//@Repository not mandatory here because this interface extends JpaRepo and the last contains @Repo Annotation
public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByFirstnameAndLastname(String firstname, String lastname);

    Optional<AppUser> findAppUserByEmailUser(String email);


    @Query("" +
            "SELECT CASE WHEN COUNT(s) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM AppUser s " +
            "WHERE s.emailUser =?1 "
    )
    Boolean selectExistsEmail(String email);


}
