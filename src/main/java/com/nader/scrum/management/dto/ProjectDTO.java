package com.nader.scrum.management.dto;

import com.nader.scrum.management.entities.AppUser;
import com.nader.scrum.management.entities.Sprint;
import lombok.Builder;

import java.util.List;

@Builder
public record ProjectDTO(Long idProject,
                         String titleProject,
                         String projectDescription,
                         List<AppUser> appUsers,
                         List<Sprint> sprints) {
}
