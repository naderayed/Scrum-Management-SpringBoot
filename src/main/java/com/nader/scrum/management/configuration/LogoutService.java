package com.nader.scrum.management.configuration;

import com.nader.scrum.management.repositories.TokenRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final TokenRepo tokenRepo;

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        final String authorizationHeader = request.getHeader("Authorization");
        final String jwtToken;
        if (authorizationHeader != null || !authorizationHeader.startsWith("Bearer ")) {
            return;
        }
        jwtToken = authorizationHeader.substring(7);
        var storedToken = tokenRepo.findByToken(jwtToken)
                .orElseThrow(() -> new RuntimeException("Token not found"));
        if (storedToken != null) {
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            tokenRepo.save(storedToken);
        }
    }
}
