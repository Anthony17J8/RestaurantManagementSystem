package ru.icoltd.rvs.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

@NotNull
@Size(min = 1)
@Documented
@Constraint(validatedBy = {UniqueUsernameValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {

    String message() default "{ru.icoltd.rvs.validation.UniqueUsername.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
