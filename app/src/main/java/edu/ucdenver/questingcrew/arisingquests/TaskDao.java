package edu.ucdenver.questingcrew.arisingquests;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import javax.security.auth.Subject;

@Dao
public interface TaskDao {
    /*
    @Query("SELECT * FROM Task WHERE id = :id")
    public Task getTask(long id);

    @Query("SELECT * FROM Task order by title")
    public Task[] getAllTasks();

    @Query("SELECT title from Task order by title")
    public String[] getAllTitles();

    @Query("SELECT * FROM Task order by dueDate")
    public Task[] getAllTasksByDate();

     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long addTask(Task task);

    // Update entire row of table
    @Update()
    void updateTask(Task task);

    @Delete()
    void deleteTask(Task task);

}
