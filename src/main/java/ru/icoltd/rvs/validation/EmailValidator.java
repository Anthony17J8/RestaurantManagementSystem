package ru.icoltd.rvs.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.icoltd.rvs.entity.User;
import ru.icoltd.rvs.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private final UserService userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isEmpty()) {
            return true;
        }
        return isNotExistUserWith(email);
    }

    private boolean isNotExistUserWith(String email) {
        boolean result = true;
        User user = userService.findUserByEmail(email);
        if (user != null) {
            log.warn("User with email '{}' already exist", email);
            result = false;
        }
        return result;
    }
}
