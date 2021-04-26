package com.github.czechp.recruitmentapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service()
class AppUserCommandRepositoryImpl implements AppUserCommandRepository{
    private final AppUserRepository appUserRepository;

    @Autowired()
    AppUserCommandRepositoryImpl(final AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public AppUser save(final AppUser appUser) {
        return appUserRepository.save(appUser);
    }
}
