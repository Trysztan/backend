package com.gym.gymapp.controllers;

import com.gym.gymapp.model.User;
import com.gym.gymapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/regist")
    public ResponseEntity<String> createUserWithImage(@ModelAttribute User user, @RequestParam("file") MultipartFile file) {
        try {
            Optional<User> createUser = userService.saveUser(user);
            if (createUser.isPresent()) {
                userService.uploadProfilePic(createUser.get().getId(), file);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/profilepic")
    public ResponseEntity<byte[]> getUserProfilePic(@PathVariable Long id) {
        Optional<User> userOptional = userService.getById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType((MediaType.IMAGE_JPEG));
            return new ResponseEntity<>(user.getImagepath(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userService.listAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/login/{username}/{password}")
    public ResponseEntity<User> userLogin(@PathVariable String username, @PathVariable String password) {
        Optional<User> loginedUser = userService.loginUser(username, password);

        return loginedUser.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        Optional<User> user = userService.getById(id);
        if (user.isPresent()) {
            userService.getProfilePic(user.get().getId());
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@ModelAttribute User updatedUser,@RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            userService.updateUser(updatedUser,file);
            if(file != null){
                userService.uploadProfilePic(updatedUser.getId(),file);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> user = userService.getById(id);
        if (user.isPresent()) {
            userService.deleteUser(user.get().getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
