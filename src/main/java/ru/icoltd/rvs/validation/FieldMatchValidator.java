package ru.icoltd.rvs.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapperImpl;
import ru.icoltd.rvs.user.RegisteredUser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, RegisteredUser> {

    private static final Logger log = LoggerFactory.getLogger(FieldMatchValidator.class);

    private String password;

    private String matchingPassword;

    private String message;

    @Override
    public boolean isValid(RegisteredUser value, ConstraintValidatorContext context) {
        boolean valid = true;
        try {
            Object first = new BeanWrapperImpl(value).getPropertyValue(password);

            Object second = new BeanWrapperImpl(value).getPropertyValue(matchingPassword);

            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();

            valid = (first == null && second == null) || (first != null && first.equals(second));
        } catch (Exception exc) {
            log.error("Error with validation password: {}", exc.getMessage());
        }
        if (!valid) {
            context
                    .buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(password)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return valid;
    }

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        password = constraintAnnotation.first();
        matchingPassword = constraintAnnotation.second();
        message = constraintAnnotation.message();
    }
}
