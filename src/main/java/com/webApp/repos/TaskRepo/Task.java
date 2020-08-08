/*
package com.webApp.repos.TaskRepo;

import com.webApp.repos.RouteRepo.Route;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// jpa entity
@Entity
// @Embeddable - defines the class as POJO to be injected to some @Entity
// access the attributes of the object just as if they were present on the entity instance itself.
// defines table name in db
@Table(name="TASKS", schema = "public")
public class Task {

    // each entity must have its primary key
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id")
    private int id;

    @Column(name="name")
    */
/* javax.validation extension to execute validation *//*

    @NotNull
    @Size(
        min = 2, max = 255
    )
    private String name;

    @Column(name="description")
    @NotNull
    @Size(
            min = 2, max = 255
    )
    private String description;
    */
/*@JoinColumn indicates the entity is the owner of the relationship and the corresponding table has a column
    with a foreign key to the referenced table.

    mappedBy indicates the entity is the inverse of the relationship.*//*

    // @ManyToOne is most effective in sql generation, so it is put to child entities
    */
/*many-to-one is the most common mapping and is the one that is normally used when creating an association to an entity.*//*

    @ManyToOne
    */
/* name = specified name for the column to contain foreign key
    * category_id - would be default
    *
    * *//*

    @JoinColumn(name = "category_id") // foreign key column in the entity
    @NotNull
    // @Basic(fetch=FetchType.LAZY) - defines loading for the column, by default it is FetchType.EAGER
    */
/*
    The only times when lazy loading of a basic mapping should be considered are when there are many
     columns in a table (for example, dozens or hundreds) or when the columns are large
     (for example, very large character strings or byte strings)
     *//*

    */
/*
    The @Lob annotation acts as the marker for CLOB and BLOB objects
    for example byte[] will be an indicator that the field is BLOB

    @Enumerated - indicates that it is enum and type of string
    @Enumerated(EnumType.STRING)
            *//*


    */
/*
    @Transient - the field will be not persisted
     *//*


    */
/*@ManyToMany
    it requires join table for primary keys
    @JoinTable(name="EMP_PROJ",
            joinColumns=@JoinColumn(name="EMP_ID"),
            inverseJoinColumns=@JoinColumn(name="PROJ_ID"))*//*


    */
/*
    it assumes that here is used @Embeddable objects as collection
    * @CollectionTable(
        name="VACATION",
        joinColumns=@JoinColumn(name="EMP_ID"))
    * *//*


    */
/*
    * @OrderBy("name ASC") - ordering
    * *//*


    private Route route;

    public String getName() {
        return name;
    }


    public Task(String name, String description, Route route) {
        this.name = name;
        this.description = description;
        this.route = route;
    }
    // must have empty public constructor
    public Task() {
    }

    public int getId() {
        return id;
    }
    public void seId(int id) {
        this.id =  id;
    }

   public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return new String("Task name is: " + getName() + " and task description: " + getDescription() +
                " and category is: " + getRoute().getName());
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
*/
