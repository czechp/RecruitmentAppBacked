package com.github.czechp.recruitmentapp.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter()
@Setter(AccessLevel.PACKAGE)
public class AppUserLoginDto {
    @NotNull(message = "Username cannot be null")
    @Length(min = 5)
    private String username;

    @NotNull(message = "Password cannot be null")
    @Length(min = 5)
    private String password;

    AppUserLoginDto() {
    }

    AppUserLoginDto(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        AppUserLoginDto that = (AppUserLoginDto) o;

        return new EqualsBuilder().append(username, that.username).append(password, that.password).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(username).append(password).toHashCode();
    }

}
