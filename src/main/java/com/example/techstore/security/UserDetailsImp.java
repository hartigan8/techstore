package com.example.techstore.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.techstore.entities.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsImp implements UserDetails{

    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private UserDetailsImp(Long id, String username, String password,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImp create(User user){
        List<GrantedAuthority> newAuthorities = new ArrayList<>(1);
        newAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new UserDetailsImp(user.getId(), user.getEmail(), user.getPassword(), newAuthorities);
    }


    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
    
}
