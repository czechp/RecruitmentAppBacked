package com.github.czechp.recruitmentapp.user;

import java.util.Optional;

interface AppUserQueryRepository {
    Optional<AppUser> findByUsername(String username);
}
