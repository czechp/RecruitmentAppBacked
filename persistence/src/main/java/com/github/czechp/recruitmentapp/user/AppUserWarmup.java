package com.github.czechp.recruitmentapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service()
@Profile("development")
class AppUserWarmup {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired()
    AppUserWarmup(final AppUserRepository appUserRepository, final PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @EventListener(ApplicationReadyEvent.class)
    void init() {
        List<AppUser> users = Arrays.asList(
                new AppUser("admin123", "admin123", "admin123@gmail.com"),
                new AppUser("superuser123", "superuser123", "superuser123@gmail.com"),
                new AppUser("user123", "user123", "user123@gmail.com")
        );

        users.stream()
                .forEach((appUser -> {
                    appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
                    appUser.setEnabled(true);
                    appUserRepository.save(appUser);
                }));

    }


}
