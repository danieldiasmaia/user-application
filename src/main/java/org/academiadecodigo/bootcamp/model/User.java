package org.academiadecodigo.bootcamp.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by codecadet on 14/07/16.
 */
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @NotNull(message = "username is mandatory")
    @NotBlank(message = "username is mandatory")
    @Pattern(regexp = "[a-z-A-Z]*", message = "username has invalid characters")
    @Column(name = "name")
    private String name;

    @NotNull
    @NotBlank
    @Size(min=3, max=64)
    @Column(name = "password")
    private String password;

    @Email
    @Column(name = "email")
    private String email;

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
