package ru.icoltd.rvs.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.icoltd.rvs.validation.FieldMatch;
import ru.icoltd.rvs.validation.UniqueUsername;
import ru.icoltd.rvs.validation.ValidEmail;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@FieldMatch(
        first = "password",
        second = "matchingPassword",
        message = "The password fields must match")
public class RegisteredUser {

    @NotNull
    @Size(min = 1)
    private String firstName;

    @NotNull
    @Size(min = 1)
    private String lastName;

    @UniqueUsername
    private String userName;

    @NotNull
    @Size(min = 1)
    private String password;

    @NotNull
    @Size(min = 1)
    private String matchingPassword;

    @ValidEmail
    private String email;

    @NotNull
    private Date dateOfBirth;

    private Set<String> roles;
}
