package com.webApp.repos.CategoryRepo;

import com.webApp.repos.TaskRepo.Task;

import javax.persistence.*;
import java.util.List;

// java jpa entity which maps to table, must be POJO, the anno marks that the class can be used to map to table
@Entity
// table name in db
@Table(name="CATEGORIES", schema = "public")
public class Category {

    // must have empty constructor to be entity

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="text_value")
    private String value;

    /*
    * @mappedBy - field in mapped entity class on which @joincolumn is set, name of variable
    * */
    @OneToMany(mappedBy = "category")
    private List<Task> tasks;

    public Category() {}
    public Category(String name, String value) {
        this.name = name;
        this.value = value;
    }

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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String toJson() {
        return "{\"name\": \"" + name + "\", \"text_value\": \"" + value + "\"}";
    }
}
