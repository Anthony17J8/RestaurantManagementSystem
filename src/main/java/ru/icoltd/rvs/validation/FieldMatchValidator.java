package ru.icoltd.rvs.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapperImpl;
import ru.icoltd.rvs.user.RegisteredUser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, RegisteredUser> {

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
