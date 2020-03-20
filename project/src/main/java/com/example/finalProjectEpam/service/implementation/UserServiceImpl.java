package com.example.finalProjectEpam.service.implementation;


import com.example.finalProjectEpam.entity.User;
import com.example.finalProjectEpam.entity.enums.RoleStatus;
import com.example.finalProjectEpam.repository.UserRepository;
import com.example.finalProjectEpam.service.serviceInterfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;
    private User user;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public User findUserByUserName(String userName) throws UsernameNotFoundException {
        return userRepository.findUserByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("No user was found with such username" + userName));
    }


    @Override
    public User addUser(User user/*,Integer id,String firstName,String lastName,String password,String userName*/) {

        entityManager.createNativeQuery("insert into users (first_name, last_name, password, role, user_name)" +
                " values (?,?,?,?,?)");
                /*.setParameter(1,"sdcs")
                .setParameter(2,user.getLastName())
                .setParameter(3, user.getPassword())
                .setParameter(4, user.getRole())
                .setParameter(5,user.getUserName());*/
        return userRepository.saveAndFlush(user);
    }


    public boolean existsUserByUserName(String userName){
        return userRepository.existsUserByUserName(userName);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

}
