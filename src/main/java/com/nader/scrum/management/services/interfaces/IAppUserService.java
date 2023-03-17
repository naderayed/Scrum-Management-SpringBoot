package com.nader.scrum.management.services.interfaces;

import com.nader.scrum.management.dto.AppUserDTO;

import java.util.List;

public interface IAppUserService {

    public AppUserDTO getAppUser(Long id);
    List<AppUserDTO> getAllUsers();

    void assignProjectToDeveloper(int projectId, int devId);

    void assignProjectToScrumMaster(int projectId, String fName, String lName);

    AppUserDTO findUserByEmail(String email);
}
