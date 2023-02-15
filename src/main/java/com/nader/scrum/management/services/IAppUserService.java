package com.nader.scrum.management.services;

import com.nader.scrum.management.dto.AppUserDTO;

import java.util.List;

public interface IAppUserService {

    List<AppUserDTO> getAllUsers();

    void assignProjectToDeveloper(int projectId, int devId);
     void assignProjectToScrumMaster(int projectId, String fName, String lName);
}
