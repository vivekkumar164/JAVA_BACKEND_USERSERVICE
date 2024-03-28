package com.userservice2.userservice2.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.userservice2.userservice2.models.Role;
import com.userservice2.userservice2.models.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter

@JsonDeserialize(as = CustomUserDetails.class)
public class CustomUserDetails implements UserDetails  {
    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }
    public CustomUserDetails(){}

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = user.getRoles();

        Collection<CustomGrantedAuthority> customGrantedAuthorities = new ArrayList<>();
        for (Role role : roles) {
            customGrantedAuthorities.add(
                    new CustomGrantedAuthority(role)
            );
        }
        return customGrantedAuthorities;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
