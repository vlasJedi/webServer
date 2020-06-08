package com.webApp.repos.UserRepo;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User {
    /*@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private int id;*/
    @Id
    @Column(name = "username")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "mail")
    private String mail;

    /*public int getId() {
        return id;
    }*/

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
