package com.github.czechp.recruitmentapp.user;


import org.springframework.data.repository.Repository;

import java.util.Optional;

@org.springframework.stereotype.Repository()
interface AppUserRepository extends Repository<AppUser, Long> {
    AppUser save(AppUser appUser);

    Optional<AppUser> findByUsername(String username);
}
