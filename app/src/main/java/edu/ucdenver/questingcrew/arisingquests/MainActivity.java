package edu.ucdenver.questingcrew.arisingquests;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;

import edu.ucdenver.questingcrew.arisingquests.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private TaskDatabase taskDatabase;
    private EditTaskDialog editTaskDialog;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setSupportActionBar(binding.toolbarMain);

        Log.i("info", "getting instance of db");
        taskDatabase = TaskDatabase.getInstance(this);
        fragmentManager = getSupportFragmentManager();
    }

    public void onResume() {
        super.onResume();
        //loadData();
    }

    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        // go to edit task activity
        if (id == R.id.action_add) {
            editTaskDialog = new EditTaskDialog(this, taskDatabase);
            editTaskDialog.show(getSupportFragmentManager(), "task");
        }

        return super.onOptionsItemSelected(item);
    }

    public void loadData() {
        // create second task list and copy all database tasks into it
        // <my second list> = taskDatabase.TaskDao().getAllTasksByDueDate();
    }

    // add task to database
    public void addTask(Task task) {
        taskDatabase.taskDao().addTask(task);
        Log.i("info", "added task: " + task.getTitle() + " to database");
        Log.i("info", "\tdescription: " + task.getDescription());
        Log.i("info", "\tdate: " + task.getDueDate());
        Log.i("info", "\timportance: " + task.getImportance());
        //taskAdapter.notifyDataSetChanged();
    }

    // delete task from database
    public void deleteTask(Task task){
        taskDatabase.taskDao().deleteTask(task);
        //taskAdapter.notifyDataSetChanged();
    }

    public void getTask(Task task){
        long taskID = task.getId();
        taskDatabase.taskDao().getTask(taskID);
        //taskAdapter.notifyDataSetChanged();
    }

    public void updateTask(Task task){
        taskDatabase.taskDao().updateTask(task);
        //taskAdapter.notifyDataSetChanged();
    }

    public void goToEditTask(View view){
        editTaskDialog = new EditTaskDialog(this, taskDatabase);
        editTaskDialog.show(fragmentManager, "task");
    }

    public void goToMentalHealth(View view){
        Log.i("info", "before starting mental health intent");
        Intent mentalHealth = new Intent(this, MentalHealth.class);
        Log.i("info", "after declaring mental health intent");
        startActivity(mentalHealth);
        Log.i("info", "after calling mental health intent");
    }

    public void editThisTask(View view, Task task){
        //pass Task (and its id) user wants to edit from the database
        editTaskDialog = new EditTaskDialog(this, taskDatabase, task, task.getId());
        editTaskDialog.show(fragmentManager, "task");
    }

    public void passUpdateStep(Substep step, int position){
        Log.i("info", "main passing step: " + step.getStep());
        editTaskDialog.editStep(step, position);
    }

    public void passAddStep(Substep step) {
        Log.i("info", "main passing step: " + step.getStep());
        editTaskDialog.addStep(step);
    }

    public void editStep(Substep substep, int position) {
        SubstepDialog substepsDialog = new SubstepDialog(substep, position);
        substepsDialog.show(fragmentManager, "substep");
    }


}