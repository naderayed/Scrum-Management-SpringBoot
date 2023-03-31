package com.nader.scrum.management.services.interfaces;

import com.nader.scrum.management.dto.AppUserDTO;
import com.nader.scrum.management.entities.AuthenticationRequest;
import com.nader.scrum.management.entities.AuthenticationResponse;
import com.nader.scrum.management.entities.RegisterRequest;

import java.util.List;

public interface IAppUserService {

    public AppUserDTO getAppUser(Long id);
    List<AppUserDTO> getAllUsers();

    void assignProjectToDeveloper(int projectId, int devId);

    void assignProjectToScrumMaster(int projectId, String fName, String lName);

    AppUserDTO findUserByEmail(String email);

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
