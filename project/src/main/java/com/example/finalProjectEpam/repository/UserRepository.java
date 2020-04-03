package com.example.finalProjectEpam.repository;

import com.example.finalProjectEpam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
/*@Transactional*/
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findUserByUserName(@Param("user") String userName);
    boolean existsUserByUserName(String userName);


 /*   @Modifying
    @Query(value = "insert into users (first_name, last_name, password, role, user_name,first_name_ukr,last_name_ukr)" +
            " values (?1,?2,?3,?4,?5,?6,?7)",nativeQuery = true)
    void addUser(String firstName,
                 String lastName,
                 String password,
                 String role,
                 String userName,
                 String firstNameUkr,
                 String lastNameUkr);*/

    void deleteUserByUserName(@Param("userName") String userName);
}
