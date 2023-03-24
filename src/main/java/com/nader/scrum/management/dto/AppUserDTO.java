package com.nader.scrum.management.dto;

import com.nader.scrum.management.entities.Role;
import lombok.Builder;


//we can create as many as we want views by creating DTOs
@Builder
public record AppUserDTO (
        Long idUser,
        String emailUser,
        String firstname,
        Role role
) {
}

