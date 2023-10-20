package com.gym.gymapp.services;

import com.gym.gymapp.model.User;
import com.gym.gymapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> loginUser(String username, String password) {
        return userRepository.login(username,password);
    }

    @Override
    public Optional<User> saveUser(User newUser) {
        if(userRepository.existsById(newUser.getId())) {
            return Optional.empty();
        }
        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        user.setHeight(newUser.getHeight());
        user.setWeight(newUser.getWeight());
        user.setImagepath(newUser.getImagepath());
        return Optional.of(userRepository.save(user));
    }

    @Override
    public Optional<User> updateUser(User user,MultipartFile file) {
        Optional<User> existingUser = userRepository.findById(user.getId());

        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setPassword(user.getPassword());
            if(file !=null) {
                userToUpdate.setImagepath(user.getImagepath());
            }
            userToUpdate.setWeight(user.getWeight());
            userToUpdate.setHeight(user.getHeight());
            User updatedUser = userRepository.save(userToUpdate);
            return Optional.of(updatedUser);
        } else {
            return Optional.empty();
        }
    }


    @Override
    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(value -> userRepository.delete(value));
    }

    @Override
    public Optional<User> uploadProfilePic(Long id, MultipartFile file){

            Optional<User> userOptional = userRepository.findById(id);
            if(userOptional.isPresent()) {
                User user = userOptional.get();
                try {
                    user.setImagepath(file.getBytes());
                    userRepository.save(user);

                } catch (IOException e) {
                    throw new RuntimeException("Hiba a kép feltöltése közben");
                }
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
    }
    @Override
    public byte[] getProfilePic(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.map(User::getImagepath).orElse(null);
    }
}
