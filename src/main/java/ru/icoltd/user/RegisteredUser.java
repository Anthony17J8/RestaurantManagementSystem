package ru.icoltd.user;

import ru.icoltd.rvs.validation.FieldMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

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

    // todo pattern
    @NotNull
    @Size(min = 1)
    private String userName;

    @NotNull
    @Size(min = 1)
    private String password;

    @NotNull
    @Size(min = 1)
    private String matchingPassword;

    @Email
    @NotNull
    @Size(min = 1)
    private String email;

    @NotNull
    private Date dateOfBirth;

    public RegisteredUser() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
