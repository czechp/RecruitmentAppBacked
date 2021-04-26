package com.github.czechp.recruitmentapp.security;

import com.github.czechp.recruitmentapp.user.AppUserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration()
class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final AppUserQueryService appUserQueryService;

    @Autowired()
    SecurityConfiguration(final AppUserQueryService appUserQueryService) {
        this.appUserQueryService = appUserQueryService;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserQueryService);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .httpBasic();
    }

    @Bean()
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}