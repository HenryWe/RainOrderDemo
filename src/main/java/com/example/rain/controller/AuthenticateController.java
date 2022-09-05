package com.example.rain.controller;

import com.example.rain.model.AuthenticationRequest;
import com.example.rain.model.AuthenticationResponse;
import com.example.rain.service.IJwtSecurityService;
import com.example.rain.component.UserDetailsJwtComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticateController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsJwtComponent userDetailsJwtComponent;
    @Autowired
    IJwtSecurityService jwtSecurityService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody AuthenticationRequest authenticationRequest
    ) throws Exception {

        try {

            authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword())
            );

        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsJwtComponent.loadUserByUsername(
                authenticationRequest.getUsername()
        );

        final String jwt = jwtSecurityService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
