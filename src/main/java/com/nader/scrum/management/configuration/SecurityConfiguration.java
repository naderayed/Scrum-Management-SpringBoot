package com.nader.scrum.management.configuration;

import com.nader.scrum.management.entities.AppUser;
import com.nader.scrum.management.entities.Role;
import com.nader.scrum.management.services.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity()
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    private final PasswordEncoder passwordEncoder;
    private final AppUserService appUserService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeHttpRequests(auth ->
                        auth.antMatchers("/")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .httpBasic();


        return httpSecurity.build();
    }


    @Bean
    UserDetailsService userDetailsService() {
        AppUser admin= appUserService.getAppUserLOG(6L);

        return new InMemoryUserDetailsManager(
                admin
        );
    }
}

