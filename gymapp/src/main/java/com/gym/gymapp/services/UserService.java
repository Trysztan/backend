package com.gym.gymapp.services;

import com.gym.gymapp.commands.UserForm;
import com.gym.gymapp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> listAll();

    Optional<User> getById(Long id);

    Optional<User> saveUser(User user);

    Optional<User> updateUser(User user);

    void deleteUser(Long id);

    User saveOrUpdateProductForm(UserForm userForm);
}
