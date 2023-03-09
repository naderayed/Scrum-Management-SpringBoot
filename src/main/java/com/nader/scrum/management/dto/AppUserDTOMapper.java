package com.nader.scrum.management.dto;

import com.nader.scrum.management.entities.AppUser;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AppUserDTOMapper implements Function<AppUser,AppUserDTO> {
    @Override

    public AppUserDTO apply(AppUser user) {
        return new AppUserDTO(
                user.getIdUser(),
                user.getEmailUser(),
                user.getFirstname(),
                user.getRole()
        );
    }
}
