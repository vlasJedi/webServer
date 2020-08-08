package com.webApp.repos.RouteRepo;

/*import com.webApp.repos.TaskRepo.Task;*/

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

// java jpa entity which maps to table, must be POJO, the anno marks that the class can be used to map to table
@Entity
// table name in db
@Table(name="ROUTES", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = {"path"}))
public class Route {

    // must have empty constructor to be entity

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id")
    private int id;

    @Column(name="path")
    @NotNull
    private String path;

    /*
    * @mappedBy - field in mapped entity class on which @joincolumn is set, name of variable
    * */
    /*@OneToMany(mappedBy = "category")
    private List<Task> tasks;*/

    public Route() {}

    public Route(String path) {
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String toJson() {
        return "{\"path\": \"" + path + "\"}";
    }
}
