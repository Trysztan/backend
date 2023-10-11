package com.gym.gymapp.converters;

import com.gym.gymapp.commands.UserForm;
import com.gym.gymapp.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UserFormToUser implements Converter<UserForm, User> {
    @Override
    public User convert(UserForm userForm) {
        User user = new User();
        if (userForm.getId() != null  && !StringUtils.isEmpty(userForm.getId())) {
            user.setId(userForm.getId());
        }
        user.setUsername(userForm.getUsername());
        return user;
    }
}
