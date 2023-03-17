package com.nader.scrum.management.dto;

import com.nader.scrum.management.entities.Project;
import lombok.Builder;

import java.util.Date;

@Builder
public record SprintDTO(Long idSprint,
                        String descriptionSprint,
                        Date startDate,
                        Date endDate,
                        Project project) {
}
