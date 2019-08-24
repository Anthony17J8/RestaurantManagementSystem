package ru.icoltd.rvs.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.icoltd.rvs.entity.User;
import ru.icoltd.rvs.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Logger log = LoggerFactory.getLogger(EmailValidator.class);

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isEmpty()) {
            return true;
        }
        return isNotExistUserWith(email) && isMatchPattern(email);
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

    private boolean isMatchPattern(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
