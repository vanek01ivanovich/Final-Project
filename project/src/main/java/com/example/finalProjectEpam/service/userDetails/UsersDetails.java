package com.example.finalProjectEpam.service.userDetails;

import com.example.finalProjectEpam.entity.User;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import java.util.Collection;
import java.util.Collections;


public class UsersDetails implements UserDetails {


    private User user;

    public UsersDetails(User userByUserName){
        this.user = userByUserName;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString()));

    }

    public String getFirstName(){
        return user.getFirstName();
    }

    @Override
    public String getPassword() {
      return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
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
        return true;
    }

    public String getLastName() {
        return user.getLastName();
    }

    public String getLastNameUkr() {
        return user.getLastNameUkr();
    }

    public String getFirstNameUkr(){
        return user.getFirstNameUkr();
    }


}
