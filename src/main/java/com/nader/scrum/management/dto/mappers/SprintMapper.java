package com.nader.scrum.management.dto.mappers;

import com.nader.scrum.management.dto.SprintDTO;
import com.nader.scrum.management.entities.Sprint;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SprintMapper implements Function<Sprint, SprintDTO> {
    @Override
    public SprintDTO apply(Sprint sprint) {
        return SprintDTO.builder()
                .idSprint(sprint.getIdSprint())
                .descriptionSprint(sprint.getDescriptionSprint())
                .endDate(sprint.getEndDate())
                .startDate(sprint.getStartDate())
                .project(sprint.getProject())
                .build();
    }
}
