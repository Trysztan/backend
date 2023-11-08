package com.gym.gymapp.services.user;

import com.gym.gymapp.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> listAll();

    Optional<User> getById(Long id);

    Optional<User> getByUsername(String username);

    Optional<User> loginUser(String username,String password);

    Optional<User> saveUser(User user);

    Optional<User> updateUser(User user,MultipartFile file);

    void deleteUser(Long id);


    Optional<User> uploadProfilePic(Long id, MultipartFile file);

    byte[] getProfilePic(Long id);
}
