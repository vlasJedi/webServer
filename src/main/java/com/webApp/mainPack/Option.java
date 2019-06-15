package com.webApp.mainPack;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name="options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="text_value")
    private String value;

    public Option(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Option() {
        this(null, null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
