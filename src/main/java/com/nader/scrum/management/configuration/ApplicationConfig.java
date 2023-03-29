package com.nader.scrum.management.configuration;


import com.nader.scrum.management.repositories.AppUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final AppUserRepo appUserRepo;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> appUserRepo.findAppUserByEmailUser(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }
}
