package com.nader.scrum.management.services;

import com.nader.scrum.management.entities.Project;

import java.util.List;

public interface IProjectService {

    List<Project> getAllProjects();

    List<Project> getProjectsByScrumMaster(String fName, String lName);

}
