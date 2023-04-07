package com.nader.scrum.management.repositories;

import com.nader.scrum.management.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token, Long> {

    @Query("""
                    select t from Token  t inner join AppUser u on t.appUser.idUser=u.idUser
                    where u.idUser = :userID and (t.revoked = false or t.expired=false )
            """)
    List<Token> findAllValidTokenByUser(long userID);

    Optional<Token> findByToken(String token);
}
