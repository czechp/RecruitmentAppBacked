package com.github.czechp.recruitmentapp.user;


import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository()
public interface AppUserRepository extends Repository<AppUser, Long> {
    AppUser save(AppUser appUser);
}
