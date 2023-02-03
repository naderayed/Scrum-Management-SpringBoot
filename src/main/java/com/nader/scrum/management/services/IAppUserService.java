package com.nader.scrum.management.services;

import com.nader.scrum.management.entities.AppUser;

import java.util.List;

public interface IAppUserService {

    List<AppUser> getAllUsers();

    void assignProjectToDeveloper(int projectId, int devId);
}
