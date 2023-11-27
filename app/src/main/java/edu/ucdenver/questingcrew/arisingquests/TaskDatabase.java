package edu.ucdenver.questingcrew.arisingquests;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
/*
 * TaskDatabase is an abstract class that creates a room database.
 * It makes sure that only one instance of it exists (no duplicates).
 * It includes two tables: Task (parent) and Substep (child).
 * And declares abstract DAO for both Task and Substep to access/edit each table.
 * @author Jayde Tu
 * @version 11302023
 */
@Database(entities = {Task.class, Substep.class}, version = 2)
public abstract class TaskDatabase extends RoomDatabase{
    private static final String DATABASE_NAME = "task.db";
    private static TaskDatabase taskDatabase;

    // Make sure only one instance of contact database exists
    // process threads
    public static TaskDatabase getInstance(Context context) {
        if (taskDatabase == null) {
            taskDatabase = Room.databaseBuilder(context, TaskDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return taskDatabase;
    }
    public abstract TaskDao taskDao();
    public abstract SubstepDao substepDao();
}
