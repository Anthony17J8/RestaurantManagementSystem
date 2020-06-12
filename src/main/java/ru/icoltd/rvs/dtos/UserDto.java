package ru.icoltd.rvs.dtos;

import lombok.*;
import ru.icoltd.rvs.validation.FieldMatch;
import ru.icoltd.rvs.validation.UniqueUsername;
import ru.icoltd.rvs.validation.ValidEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldMatch(
        first = "password",
        second = "matchingPassword",
        message = "Passwords must match")
public class UserDto implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 1)
    private String firstName;

    @NotNull
    @Size(min = 1)
    private String lastName;

    @UniqueUsername
    private String username;

    @NotNull
    @Size(min = 1)
    private String password;

    @NotNull
    @Size(min = 1)
    private String matchingPassword;

    @ValidEmail
    @Email
    private String email;

    @NotNull
    private Date dateOfBirth;

    private Set<String> roles;
}
