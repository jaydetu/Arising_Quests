package edu.ucdenver.questingcrew.arisingquests;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Task {
    // declare class fields
    @PrimaryKey(autoGenerate = true) // primary key automatically adds non-null constraint
    @ColumnInfo(name = "id") //annotations are "pre-processors" and don't need semicolon
    private long id;
    @NonNull
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "importance")
    private String importance;
    @ColumnInfo(name = "dueDate")
    private String dueDate;
    @ColumnInfo(name = "description")
    private String description;
    //@ColumnInfo(name = "substeps")
    //private ArrayList<Substep> substeps;

    // constructor
    public Task(@NonNull String title, String importance, String dueDate, String description){
        this.title = title;
        this.importance = importance;
        this.dueDate = dueDate;
        this.description = description;
    }

    // getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
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

    /*
    public ArrayList<Substep> getSubsteps() {
        return substeps;
    }

    public void setSubsteps(ArrayList<Substep> substeps) {
        this.substeps = substeps;
    }

     */
}

