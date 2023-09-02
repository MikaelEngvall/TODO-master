package lexicon.data.impl;

import lexicon.utils.StringValidator;

import java.util.Collection;
import java.util.Objects;

public class AppUser {

    // Fields

    private String username;
    private String password;
    private AppRole role;

    // Constructor(s)

    public AppUser(String username, String password, AppRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }


    // Methods

    // Overridden methods from Object

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return Objects.equals(username, appUser.username) && role == appUser.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, role);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "username='" + username + '\'' +
                ", role=" + role +
                '}';
    }

    // Getters & Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        StringValidator.validateStringNotEmpty(username);
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        StringValidator.validateStringNotEmpty(password);
        this.password = password;
    }

    public AppRole getRole() {
        return role;
    }

    public void setRole(AppRole role) {
//        StringValidator.validateStringNotEmpty(role);
        this.role = role;
    }
}
