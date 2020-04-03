package com.example.finalProjectEpam.service.serviceInterfaces;

import com.example.finalProjectEpam.entity.User;
import org.springframework.ui.Model;

import java.util.List;

public interface UserService {
    //User addUser(User user);
    boolean addUser(User user);
    void deleteUser(User user);
    List<User> getAllUsers();
    void deleteAllUsers();

    boolean saveUser();

    void getLocale(Model model);


    void updateUser(User user,String oldUserName);
}
