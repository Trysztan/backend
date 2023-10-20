package com.gym.gymapp.repositories;

import com.gym.gymapp.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = :username")

    Optional<User> findByUsername(@Param("username") String username);
    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")

    Optional<User> login(@Param("username") String username, @Param("password") String password);

}
