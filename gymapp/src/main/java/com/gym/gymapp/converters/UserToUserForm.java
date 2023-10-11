package com.gym.gymapp.converters;

import com.gym.gymapp.commands.UserForm;
import com.gym.gymapp.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserForm implements Converter<User, UserForm> {
    @Override
    public UserForm convert(User user) {
        UserForm userForm = new UserForm();
        userForm.setId(user.getId());
        userForm.setUsername(user.getUsername());
        return userForm;
    }
}
