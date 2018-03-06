package model;

import model.annotation.Column;
import model.annotation.PrimaryKey;
import model.annotation.Table;

@Table(name = "course")
public class Course {
    @PrimaryKey
    @Column(name = "id", length = 5)
    private int id;
    @Column(name = "name", length = 20)
    private String name;
    @Column(name = "description", length = 40)
    private String description;

    public Course() {
    }

    public Course(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%-5s %-15s %s", id, name, description);
    }
}