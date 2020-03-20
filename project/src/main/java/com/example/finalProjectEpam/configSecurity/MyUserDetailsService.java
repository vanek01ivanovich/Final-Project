package com.example.finalProjectEpam.configSecurity;

import com.example.finalProjectEpam.service.implementation.UserServiceImpl;
import com.example.finalProjectEpam.service.userDetails.UsersDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {
    private UserServiceImpl userServiceImpl;


    @Autowired
    public MyUserDetailsService(UserServiceImpl userServiceImpl){
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return new UsersDetails(userServiceImpl.findUserByUserName(userName));
    }
}
