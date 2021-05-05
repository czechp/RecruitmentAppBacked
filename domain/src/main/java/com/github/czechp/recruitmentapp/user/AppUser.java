package com.github.czechp.recruitmentapp.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Entity()
@Table(name = "users")
@Getter()
@Setter(value = AccessLevel.PACKAGE)
class AppUser implements UserDetails {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Username cannot be null")
    @NotBlank(message = "Username cannot be blank")
    @Length(min = 5, message = "Username has to got minimum 5 characters")
    private String username;

    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    @Length(min = 5, message = "Password has to got minimum 5 characters")
    private String password;

    @Email(message = "Incorrect email format")
    private String email;

    private String verificationToken;

    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private AppUserRole role;

    @PersistenceConstructor()
    AppUser() {
        this.verificationToken = UUID.randomUUID().toString();
        this.role = AppUserRole.USER;
    }

    public AppUser(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.verificationToken = UUID.randomUUID().toString();
        this.role = AppUserRole.USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + this.role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        AppUser appUser = (AppUser) o;

        return new EqualsBuilder().append(id, appUser.id).append(enabled, appUser.enabled).append(username, appUser.username).append(password, appUser.password).append(email, appUser.email).append(verificationToken, appUser.verificationToken).append(role, appUser.role).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(username).append(password).append(email).append(verificationToken).append(enabled).append(role).toHashCode();
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", role=" + role +
                '}';
    }
}
