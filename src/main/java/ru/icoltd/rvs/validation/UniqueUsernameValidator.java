package ru.icoltd.rvs.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.icoltd.rvs.entity.User;
import ru.icoltd.rvs.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private static final Logger log = LoggerFactory.getLogger(UniqueUsernameValidator.class);

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext context) {
        if (userName == null || userName.isEmpty()) {
            return true;
        }
        User result = null;
        try {
            result = userService.findUserByUserName(userName);
        } catch (UsernameNotFoundException exc) {
            log.info("Registration: username '{}' is not taken.", userName);
        }
        return result == null;
    }
}
