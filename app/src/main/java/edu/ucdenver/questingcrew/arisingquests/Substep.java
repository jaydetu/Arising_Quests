package edu.ucdenver.questingcrew.arisingquests;

import androidx.annotation.NonNull;
import androidx.room.Ignore;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Insert;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;
/*
 * Substep is an object class. It is a child databse table of Task.
 * It's taskId is the foreign key that corresponds to Task's id.
 * Substep includes fields: id, taskId, and step (the text describing what the step is)
 * @author Jayde Tu
 * @version 11302023
 */
@Entity(foreignKeys = @ForeignKey(entity = Task.class,
        parentColumns = "id", childColumns = "taskId", onDelete = CASCADE))
public class Substep {

    @PrimaryKey(autoGenerate = true) // primary key automatically adds non-null constraint
    @ColumnInfo(name = "id") //annotations are "pre-processors" and don't need semicolon
    private long id;
    @ColumnInfo(name = "taskId") // task (found by ID number) that substeps are a part of
    private long taskId;
    @NonNull
    @ColumnInfo(name = "step")
    private String step;

    public Substep(){
        //empty constructor
    }
    public Substep(@NonNull String step){
        this.step = step;
    }
    public Substep(@NonNull String step, long taskId){
        this.step = step;
        this.taskId = taskId;
    }
    // getters and setters
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getTaskId() {
        return taskId;
    }
    public void setTaskId(long id) {
        this.taskId = id;
    }
    @NonNull
    public String getStep() {
        return step;
    }
    public void setStep(@NonNull String step) {
        this.step = step;
    }



}
