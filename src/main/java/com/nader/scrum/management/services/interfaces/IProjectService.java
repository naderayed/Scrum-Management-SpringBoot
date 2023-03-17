package com.nader.scrum.management.services.interfaces;

import com.nader.scrum.management.dto.ProjectDTO;
import com.nader.scrum.management.dto.mappers.ProjectDTOMapper;
import com.nader.scrum.management.entities.Project;

import java.util.List;

public interface IProjectService {

    List<ProjectDTO> getAllProjects();

    List<ProjectDTO> getProjectsByScrumMaster(String fName, String lName);

}
