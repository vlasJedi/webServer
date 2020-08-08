package com.webApp.repos.UserRepo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "USERS", uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private int id;

    @Column(name = "username")
    //@OneToOne(mappedBy = "username")
    @NotNull
    private String name;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "mail")
    @NotNull
    private String mail;

    public int getId() {
        return id;
    }

    public User() {}

    public User(String username, String password, String mail) {
        this.name = username;
        this.password = password;
        this.mail = mail;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
