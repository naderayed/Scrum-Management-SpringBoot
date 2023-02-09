package com.nader.scrum.management.controllers;

import com.nader.scrum.management.entities.AppUser;
import com.nader.scrum.management.services.IAppUserService;
import com.nader.scrum.management.services.ICrud;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping to change the prefix pf the end-point
@RequiredArgsConstructor
public class AppUserController {

    private final ICrud<AppUser> userICrud;
    private final IAppUserService iAppUserService;

    @PostMapping("addUser")
    public ResponseEntity<AppUser> addUser(@RequestBody AppUser user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userICrud.create(user));
    }

    @PutMapping("updateUser")
    public ResponseEntity<AppUser> updateUser(@RequestBody AppUser user) {
        return ResponseEntity.status(HttpStatus.OK).body(userICrud.update(user));
    }

    @GetMapping("getUser")
    public ResponseEntity<AppUser> getUser(@RequestParam Long id) {
       return ResponseEntity.status(HttpStatus.OK).body(userICrud.get(id));
    }

    @DeleteMapping("deleteUser")
    public ResponseEntity<String> deleteUser(@RequestParam Long id) {
        userICrud.delete(id);
    return ResponseEntity.accepted().body("User with  Id "+id+" successfully deleted");
    }

    @GetMapping("getAllUsers")
    public ResponseEntity<List<AppUser>> getAllUsers() {
        return ResponseEntity.ok().body(iAppUserService.getAllUsers());
    }

    @PutMapping("assignProjectToDeveloper")
    public ResponseEntity<String> assignProjectToDeveloper(@RequestParam int projectId, int devId) {
        iAppUserService.assignProjectToDeveloper(projectId, devId);
        return ResponseEntity.accepted().body("Updated successfully");
    }

    @PutMapping("assignProjectToScrumMaster")
    public ResponseEntity<String> assignProjectToScrumMaster(int projectId, String fName, String lName) {
        iAppUserService.assignProjectToScrumMaster(projectId, fName, lName);
       return ResponseEntity.accepted().body("Updated successfully");
    }
}
