package com.example.finalProjectEpam.service.serviceInterfaces;

import com.example.finalProjectEpam.entity.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    void deleteUser(User user);
    List<User> getAllUsers();
    void deleteAllUsers();



    void updateUser(User user,String oldUserName);
}
