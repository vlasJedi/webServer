package com.webApp.mainPack;
import javax.persistence.*;

@Entity
@Table(name="options")
public class Option implements EntityActionable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="text_value")
    private String value;
    public int getId() {
        return id;
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
