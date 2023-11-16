package edu.ucdenver.questingcrew.arisingquests;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class}, version = 1)
public abstract class TaskDatabase extends RoomDatabase{
    private static final String DATABASE_NAME = "task.db";
    private static TaskDatabase taskDatabase;

    // Make sure only one instance of contact database exists
    // process threads
    public static TaskDatabase getInstance(Context context) {
        if (taskDatabase == null) {
            taskDatabase = Room.databaseBuilder(context, TaskDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return taskDatabase;
    }
    public abstract TaskDao contactDao();
}
