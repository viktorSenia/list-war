package com.leo.test.list.war.util;

import com.leo.test.list.war.model.User;
import com.leo.test.list.war.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        if (userRepository.findOneByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "error.duplicate.username", "error.duplicate.username");
        }
        if (userRepository.findOneByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "error.duplicate.email", "error.duplicate.email");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "error.diff.passwordConfirm", "error.diff.passwordConfirm");
        }
    }
}
