package com.example.finalProjectEpam.service.implementation;


import com.example.finalProjectEpam.entity.User;
import com.example.finalProjectEpam.entity.enums.RoleStatus;
import com.example.finalProjectEpam.repository.UserRepository;
import com.example.finalProjectEpam.service.serviceInterfaces.UserService;
import com.example.finalProjectEpam.service.userDetails.UsersDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.management.relation.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;
    private User user;


    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public User findUserByUserName(String userName) throws UsernameNotFoundException {
        return userRepository.findUserByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("No user was found with such username" + userName));
    }


    @Override
    public boolean addUser(User user) {
        if (userRepository.existsUserByUserName(user.getUserName())){
           return false;
        }
        user.setRole(RoleStatus.ROLE_USER);
        userRepository.saveAndFlush(user);
        return true;
    }

    @Override
    public void updateUser(User user,String oldUserName) {

        Locale locale = LocaleContextHolder.getLocale();

        if (locale == Locale.ENGLISH){
            userRepository.updateUser(user.getUserName(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getRole().toString(),
                    oldUserName);
        }else {
            userRepository.updateUserUkr(user.getUserName(),
                    user.getFirstNameUkr(),
                    user.getLastNameUkr(),
                    user.getRole().toString(),
                    oldUserName);
        }

    }



    public boolean existsUserByUserName(String userName){
        return userRepository.existsUserByUserName(userName);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.deleteUserByUserName(user.getUserName());
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Override
    public boolean saveUser() {
        return true;
    }

    @Override
    public void getLocale(Model model) {
        Locale locale = LocaleContextHolder.getLocale();
        model = (locale == Locale.ENGLISH) ? model.addAttribute("type","hidden"):
                                             model.addAttribute("type","NotHidden");


    }

    @Override
    public void getLocale(Model model, UsersDetails user) {
        Locale locale = LocaleContextHolder.getLocale();
        String userName = (locale == Locale.ENGLISH) ? user.getFirstName()+" "+user.getLastName():
                                                       user.getFirstNameUkr()+" "+user.getLastNameUkr() ;

        model.addAttribute("username",userName);

    }


}
