package com.webApp.repos.TaskRepo;

import com.webApp.repos.OptionRepo.Option;

import javax.persistence.*;
import java.util.List;

// jpa entity
@Entity
// defines table name in db
@Table(name="tasks", schema = "public")
public class Task {

    // each entity must have its primary key
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;
    @Column(name="options")
    // Go look over on the bean 'option' property named 'task' on the thing I have a collection of to find the configuration
    @OneToMany(mappedBy = "task")
    private List<Option> options;

    public String getName() {
        return name;
    }


    public Task(String name, List<Option> options) {
        this.name = name;
        this.options = options;
    }
    // must have empty public constr
    public Task() {
    }

    public int getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }

    // maps to another entities
    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}
