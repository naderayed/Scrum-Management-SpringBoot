package com.nader.scrum.management.services;

import com.nader.scrum.management.dto.AppUserDTO;
import com.nader.scrum.management.dto.mappers.AppUserDTOMapper;
import com.nader.scrum.management.entities.AppUser;
import com.nader.scrum.management.entities.Project;
import com.nader.scrum.management.entities.Role;
import com.nader.scrum.management.repositories.AppUserRepo;
import com.nader.scrum.management.repositories.ProjectRepo;
import com.nader.scrum.management.services.interfaces.IAppUserService;
import com.nader.scrum.management.services.interfaces.ICrud;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

//DI with Lombok features
public class AppUserService implements IAppUserService, ICrud<AppUser> {

    private final AppUserRepo appUserRepo;
    private final ProjectRepo projectRepo;
    private final AppUserDTOMapper appUserDTOMapper;

    private final PasswordEncoder passwordEncoder;
    //  private final IEmailService iEmailService;

    public AppUserService(AppUserRepo appUserRepo, ProjectRepo projectRepo, AppUserDTOMapper appUserDTOMapper, PasswordEncoder passwordEncoder) {
        this.appUserRepo = appUserRepo;
        this.projectRepo = projectRepo;
        this.appUserDTOMapper = appUserDTOMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public AppUser create(AppUser appUser) {
        Boolean found = appUserRepo.selectExistsEmail(appUser.getEmailUser());
        if (Boolean.FALSE.equals(found)) {
//            EmailDetails emailDetails = EmailDetails.builder()
//                    .msgBody("Welcome To Scrum Management your role is "+appUser.getRole().toString()+".")
//                    .subject("WELCOME")
//                  .recipient(appUser.getEmailUser()).build();
//            iEmailService.sendSimpleMail(emailDetails);
            appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
            return appUserRepo.save(appUser);

        }

        throw new NotFoundException("Email is taken, try another Email!");
    }

    @Override
    public AppUser get(Long id) {
        return null;
    }


    public AppUserDTO getAppUser(Long id) {
        AppUser appUser = appUserRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("No User With ID :" + id));
        return AppUserDTO.builder()
                .idUser(appUser.getIdUser())
                .emailUser(appUser.getEmailUser())
                .firstname(appUser.getFirstname())
                .role(appUser.getRole())
                .build();

    }
    public AppUser getAppUserLOG(Long id){
        return appUserRepo.findById(id).orElseThrow(() -> new RuntimeException("No User With Id "+id));
    }


    @Override
    public AppUser update(AppUser appUser) {
        if (appUser != null){
            appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
            return appUserRepo.save(appUser);
        }
        throw  new RuntimeException("No User Found to Update!!");
    }

    @Override
    public void delete(Long id) {
        Optional<AppUser> appUser = appUserRepo.findById(id);
        appUser.ifPresent(appUserRepo::delete);

        throw new NotFoundException("No User With ID " + id);
    }


    @Override
    public List<AppUserDTO> getAllUsers() {
        return appUserRepo.findAll()
                .stream()
                .map(appUserDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public void assignProjectToDeveloper(int projectId, int devId) {
        Project project = projectRepo.findById((long) projectId)
                .orElseThrow(() -> new NotFoundException("No Project with ID: " + projectId));
        AppUser appUser = appUserRepo.findById((long) devId)
                .orElseThrow(() -> new NotFoundException("No Developer with ID: " + devId));
        if (appUser.getRole() == Role.ROLE_DEVELOPER) {
            appUser.getDevelopersProjects().add(project);
            appUserRepo.save(appUser);
        } else
            throw new RuntimeException("User Must be Developer Role");

    }

    @Override
    public void assignProjectToScrumMaster(int projectId, String fName, String lName) {

        AppUser appUser = appUserRepo.findByFirstnameAndLastname(fName, lName)
                .orElseThrow(() -> new NotFoundException("No User found named " + fName + " " + lName));
        Project project = projectRepo.findById((long) projectId)
                .orElseThrow(() -> new NotFoundException("No Project found With Id " + projectId));
        appUser.getScrumProjects().add(project);
        appUserRepo.save(appUser);
    }

    @Override
    public AppUserDTO findUserByEmail(String email) {
        AppUser appUser = appUserRepo.findAppUserByEmailUser(email)
                .orElseThrow(() -> new NotFoundException("No User With Email : " + email));

        return new AppUserDTO(appUser.getIdUser(),
                appUser.getEmailUser(),
                appUser.getFirstname(),
                appUser.getRole());
    }
}
