package com.example.finalProjectEpam.repository;

import com.example.finalProjectEpam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    //@Query("select user_id,first_name,user_name from users where user_name = :user")
    Optional<User> findUserByUserName(@Param("user") String userName);
    boolean existsUserByUserName(String userName);
}
