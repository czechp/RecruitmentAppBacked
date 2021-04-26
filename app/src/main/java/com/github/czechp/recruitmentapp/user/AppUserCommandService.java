package com.github.czechp.recruitmentapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service()
class AppUserCommandService {
    private final AppUserCommandRepository appUserCommandRepository;

    @Autowired()
    AppUserCommandService(final AppUserCommandRepository appUserCommandRepository) {
        this.appUserCommandRepository = appUserCommandRepository;
    }
}
