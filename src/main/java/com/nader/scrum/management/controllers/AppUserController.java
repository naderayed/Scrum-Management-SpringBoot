package com.nader.scrum.management.controllers;

import com.nader.scrum.management.entities.AppUser;
import com.nader.scrum.management.services.IAppUserService;
import com.nader.scrum.management.services.ICrud;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping to change the prefix pf the end-point
@RequiredArgsConstructor
public class AppUserController {

    private final ICrud<AppUser> userICrud;
    private final IAppUserService iAppUserService;

    @PostMapping("addUser")
    public AppUser addUser(@RequestBody AppUser user) {
        return userICrud.create(user);
    }

    @PutMapping("updateUser")
    public AppUser updateUser(@RequestBody AppUser user) {
        return userICrud.update(user);
    }

    @GetMapping("getUser")
    public AppUser getUser(@RequestParam Long id) {
        return userICrud.get(id);
    }

    @DeleteMapping("deleteUser")
    public void deleteUser(@RequestParam Long id) {
        userICrud.delete(id);
    }

    @GetMapping("getAllUsers")
    public List<AppUser> getAllUsers() {
        return iAppUserService.getAllUsers();
    }

    @PutMapping("assignProjectToDeveloper")
    public void assignProjectToDeveloper(@RequestParam int projectId, int devId) {
        iAppUserService.assignProjectToDeveloper(projectId, devId);
    }

    @PutMapping("assignProjectToScrumMaster")
    public void assignProjectToScrumMaster(int projectId, String fName, String lName) {
        iAppUserService.assignProjectToScrumMaster(projectId, fName, lName);
    }
}
