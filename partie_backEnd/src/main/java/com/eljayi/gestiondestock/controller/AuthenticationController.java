package com.eljayi.gestiondestock.controller;

import com.eljayi.gestiondestock.dto.auth.AuthenticationRequest;
import com.eljayi.gestiondestock.dto.auth.AuthenticationResponse;
import com.eljayi.gestiondestock.services.auth.ApplicationUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.eljayi.gestiondestock.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final ApplicationUserDetailsService userDetailsService;

    public AuthenticationController(AuthenticationManager authenticationManager, ApplicationUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getLogin());
        return  ResponseEntity.ok(AuthenticationResponse.builder().accessToken("dummy_access_token").build());
    }
}
