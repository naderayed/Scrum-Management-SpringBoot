package com.nader.scrum.management.dto;

import com.nader.scrum.management.entities.AppUser;

import java.util.function.Function;

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
