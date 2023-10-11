package com.gym.gymapp.repositories;

import com.gym.gymapp.model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
}
