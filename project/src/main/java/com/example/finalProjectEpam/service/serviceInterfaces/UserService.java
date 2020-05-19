package com.example.finalProjectEpam.service.serviceInterfaces;

import com.example.finalProjectEpam.entity.User;
import com.example.finalProjectEpam.service.userDetails.UsersDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserService {
    //User addUser(User user);
    boolean addUser(User user);
    void deleteUser(User user);
    List<User> getAllUsers();
    void deleteAllUsers();

    boolean saveUser();

    void getLocale(Model model, UsersDetails user);
    void getLocale(Model model);


    void updateUser(User user,String oldUserName);
}
