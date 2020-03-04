package com.webApp.repos.OptionRepo;
import com.webApp.repos.TaskRepo.Task;

import javax.persistence.*;

// java jpa entity which maps to table, must be POJO, the anno marks that the class can be used to map to table
@Entity
// table name in db
@Table(name="options", schema = "public")
public class Option {

    // must have empty constructor to be entity

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="text_value")
    private String value;
    /*// @ManyToOne is most effective in sql generation, so it is put to child entities
    @ManyToOne()
    // column in the table which points to another table, so it is foreign key
    @JoinColumn(name="task_id")
    private Task task;*/

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

   /* public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }*/
}
