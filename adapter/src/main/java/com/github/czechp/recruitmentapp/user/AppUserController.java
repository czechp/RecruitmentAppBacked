package com.github.czechp.recruitmentapp.user;

import com.github.czechp.recruitmentapp.user.dto.AppUserLoginDto;
import com.github.czechp.recruitmentapp.utility.security.JwtAuthorizationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController()
@RequestMapping("/api/users")
@CrossOrigin("*")
class AppUserController {
    private final JwtAuthorizationTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;

    @Autowired()
    AppUserController(final JwtAuthorizationTokenService jwtTokenService, final AuthenticationManager authenticationManager) {
        this.jwtTokenService = jwtTokenService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
        //TODO: TEST IT
    String login(
            @RequestBody() @Valid() AppUserLoginDto appUserLoginDto,
            HttpServletRequest request
    ) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUserLoginDto.getUsername(), appUserLoginDto.getPassword()));

        return jwtTokenService.generateToken(appUserLoginDto.getUsername(), request.getRemoteAddr());
    }
}
