package com.gym.gymapp.services;

import com.gym.gymapp.commands.UserForm;
import com.gym.gymapp.model.User;
import com.gym.gymapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public Optional<User> getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    @Override
    public Optional<User> saveUser(User user) {
        if(userRepository.existsById(user.getId())) {
            return Optional.empty();
        }
        return Optional.of(userRepository.save(user));
    }

    @Override
    public Optional<User> updateUser(User user) {
        Optional<User> existingUser = userRepository.findById(user.getId());

        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();
            userToUpdate.setUsername(user.getUsername());
            User updatedUser = userRepository.save(userToUpdate);
            return Optional.of(updatedUser);
        } else {
            return Optional.empty();
        }
    }


    @Override
    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
        }
    }

    @Override
    public User saveOrUpdateProductForm(UserForm userForm) {
        return null;
    }
}
