package com.github.czechp.recruitmentapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service()
class AppUserQueryRepositoryImpl implements AppUserQueryRepository {
    private final AppUserRepository appUserRepository;

    @Autowired()
    AppUserQueryRepositoryImpl(final AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public Optional<AppUser> findByUsername(final String username) {
        return appUserRepository.findByUsername(username);
    }
}
