package edu.ucdenver.questingcrew.arisingquests;

public class Task {
    // declare class fields
    private int id;
    private String title;
    private String importance;
    private String dueDate;
    private String description;

    // constructor
    public Task(String title, String importance, String dueDate, String description){
        this.id = 0;
        this.title = title;
        this.importance = importance;
        this.dueDate = dueDate;
        this.description = description;
    }

    // getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

