package com.github.czechp.recruitmentapp.user;

import com.github.czechp.recruitmentapp.user.dto.AppUserLoginDto;
import com.github.czechp.recruitmentapp.utility.security.JwtAuthorizationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/api/users")
@CrossOrigin("*")
class AppUserController {
    private final AppUserQueryService appUserQueryService;
    private final JwtAuthorizationTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;

    @Autowired()
    AppUserController(final AppUserQueryService appUserQueryService, final JwtAuthorizationTokenService jwtTokenService, final AuthenticationManager authenticationManager) {
        this.appUserQueryService = appUserQueryService;
        this.jwtTokenService = jwtTokenService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    //TODO: TEST IT
    String login(
            @RequestBody() @Valid() AppUserLoginDto appUserLoginDto
    ){
        UserDetails userDetails = appUserQueryService.loadUserByUsername(appUserLoginDto.getUsername());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUserLoginDto.getUsername(), appUserLoginDto.getPassword()));


        return jwtTokenService.generateToken(appUserLoginDto.getUsername());
    }
}
