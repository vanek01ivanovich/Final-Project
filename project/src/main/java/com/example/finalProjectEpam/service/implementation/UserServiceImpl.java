package com.example.finalProjectEpam.service.implementation;


import com.example.finalProjectEpam.entity.User;
import com.example.finalProjectEpam.entity.enums.RoleStatus;
import com.example.finalProjectEpam.repository.UserRepository;
import com.example.finalProjectEpam.service.serviceInterfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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

        entityManager.createNativeQuery("insert into users (first_name, last_name, password, role, user_name,first_name_ukr,last_name_ukr)" +
                " values (?,?,?,?,?,?,?)");

        return userRepository.saveAndFlush(user);
    }

    @Override
    public void updateUser(User user,String oldUserName) {
        System.out.println(user.getUserName());
        String UPDATE_SQL;
        SqlParameterSource parameters;

        Locale locale = LocaleContextHolder.getLocale();
        if (locale == Locale.ENGLISH){
            UPDATE_SQL = "UPDATE users SET user_name=:username,first_name=:firstName," +
                    "last_name=:lastName,role=:role where user_name=:oldUserName";

            parameters = new MapSqlParameterSource()
                    .addValue("username",user.getUserName())
                    .addValue("firstName",user.getFirstName())
                    .addValue("lastName",user.getLastName())
                    .addValue("role",user.getRole().toString())
                    .addValue("oldUserName",oldUserName);

        }else {


            UPDATE_SQL = "UPDATE users SET user_name=:username,first_name_ukr=:firstName_ukr," +
                    "last_name_ukr=:lastName_ukr,role=:role where user_name=:oldUserName";

            parameters = new MapSqlParameterSource()
                    .addValue("username",user.getUserName())
                    .addValue("firstName_ukr",user.getFirstNameUkr())
                    .addValue("lastName_ukr",user.getLastNameUkr())
                    .addValue("role",user.getRole().toString())
                    .addValue("oldUserName",oldUserName);

        }




        namedParameterJdbcTemplate.update(UPDATE_SQL,parameters);
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



}
