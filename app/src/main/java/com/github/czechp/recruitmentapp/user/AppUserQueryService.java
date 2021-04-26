package com.github.czechp.recruitmentapp.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service()
public class AppUserQueryService implements UserDetailsService {

    private final AppUserQueryRepository appUserQueryRepository;

    @Autowired()
    AppUserQueryService(final AppUserQueryRepository appUserQueryRepository) {
        this.appUserQueryRepository = appUserQueryRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String s) throws UsernameNotFoundException {
        AppUser appuser = appUserQueryRepository.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("Username not exists"));
        return appuser;
    }
}

