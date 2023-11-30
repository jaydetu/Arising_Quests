package edu.ucdenver.questingcrew.arisingquests;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import edu.ucdenver.questingcrew.arisingquests.databinding.DialogEditTaskBinding;
/*
 * EditTaskDialog is the dialog fragment that controls adding a new task and editing an existing task.
 * EditTaskDialog includes:
 *      - menu toolbar with buttons to save and cancel Task
 *      - input views for Task's title, description, and dueDate
 *      - adds a mask to dueDate to view date as MM/DD/YYYY instead of MMDDYYYY
 *      - recycler view that displays substeps (when a new step is created, it updates to show it)
 *      - "Add Substep" button that opens up the SubstepDialog to create a new step
 *      - "Clear" button to reset input feilds
 *      - "Save" button to save the Task
 * @author Jayde Tu
 * @version 11302023
 */
public class EditTaskDialog extends DialogFragment {

    private DialogEditTaskBinding binding;
    private MainActivity mainActivity;
    private TaskDatabase taskDatabase;
    private Substep[] substepList;
    private SubstepsAdapter substepAdapter;
    private Boolean editingTask;
    private Task currentTask;
    private long currentTaskID;
    private boolean setTask1;

    // Constructor when creating a new task
    public EditTaskDialog(MainActivity mainActivity, TaskDatabase taskDatabase) {
        this.mainActivity = mainActivity;
        this.taskDatabase = taskDatabase;
        this.currentTask = new Task(); //create empty task to populate later
        this.currentTaskID = taskDatabase.taskDao().addTask(currentTask); //get task ID of task currently being created
        editingTask = false;
    }

    // Constructor when editing an existing task
    public EditTaskDialog(MainActivity mainActivity, TaskDatabase taskDatabase, Task task) {
        this.mainActivity = mainActivity;
        this.taskDatabase = taskDatabase;
        this.currentTask = task;
        this.currentTaskID = task.getId();
        editingTask = true;
    }
    public EditTaskDialog(MainActivity mainActivity, TaskDatabase taskDatabase, Task task, long taskID) {
        this.mainActivity = mainActivity;
        this.taskDatabase = taskDatabase;
        this.currentTask = task;
        this.currentTaskID = taskID;
        editingTask = true;
        Log.i("info", "Task: " + task.getTitle());
        Log.i("info", "Task: " + currentTask.getTitle());
    }
    public EditTaskDialog(MainActivity mainActivity, TaskDatabase taskDatabase, Task task, long taskID, boolean setTask1) {
        this.mainActivity = mainActivity;
        this.taskDatabase = taskDatabase;
        this.currentTask = task;
        this.currentTaskID = taskID;
        this.setTask1 = setTask1;
        editingTask = true;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        binding = DialogEditTaskBinding.inflate(LayoutInflater.from (getContext()));

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(binding.getRoot());
        binding.editTaskToolbar.inflateMenu(R.menu.menu_edit_task);

        binding.editTaskToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.action_cancel){
                    clearClicked();
                    dismiss();
                }
                else if (id == R.id.action_save){
                    saveClicked();
                    dismiss();
                }

                return false;
            }
        });

        binding.elevatedButtonAddSubsteps.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addSubstep(view);
            }
        });
        binding.elevatedButtonSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                saveClicked();
            }
        });
        binding.elevatedButtonCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                clearClicked();
            }
        });

        // Add mask to textInputDueDate to view date as MM/DD/YYYY instead of MMDDYYYY
        binding.textInputDueDate.addTextChangedListener(new TextWatcher(){
            private String current = "";
            private String mmddyyyy = "MMDDYYYY";

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Do nothing
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]|\\.", "");
                    String cleanC = current.replaceAll("[^\\d.]|\\.", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8 ){
                        clean = clean + mmddyyyy.substring(clean.length());
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));
                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    binding.textInputDueDate.setText(current);
                    binding.textInputDueDate.setSelection(sel < current.length() ? sel : current.length());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Do nothing
            }
        });

        // create recycler
        substepList = new Substep[0];
        substepAdapter = new SubstepsAdapter(mainActivity, substepList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.content.recyclerView.setLayoutManager(layoutManager);
        //binding.content.recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        binding.content.recyclerView.setAdapter(substepAdapter);

        if (setTask1){
            Substep substep1 = new Substep("Dialogs and Fragments", currentTaskID);
            Substep substep2 = new Substep("Room Database", currentTaskID);
            Substep substep3 = new Substep("Android Life Cycle", currentTaskID);
            addStep(substep1);
            addStep(substep2);
            addStep(substep3);
        }
        if (editingTask) {
            setTask();
        }
        return builder.create();
    }

    public void setTask1Substeps(){
        Substep[] tempList = substepList;
        tempList = taskDatabase.substepDao().getTaskSubsteps(currentTaskID);
        substepList = tempList;
    }
    // update substepList from database to pass to substep adapter
    public void updateSubstepList(){
        substepList = taskDatabase.substepDao().getTaskSubsteps(currentTaskID);
    }


    // create and show substep dialog fragment
    public void addSubstep(View view){
        FragmentManager manager = getParentFragmentManager();
        SubstepDialog substepsDialog = new SubstepDialog(currentTaskID);
        substepsDialog.show(manager, "substep");
    }

    // update existing step in database
    public void editStep(Substep substep, int position){
        Log.i("info", "in edit task, updating step: " + substep.getStep() + " to database");
        taskDatabase.substepDao().updateSubstep(substep);
        Log.i("info", "updated step: " + taskDatabase.substepDao().getSubstep(substep.getId()) + " to database");
        substepList[position] = substep;
        substepAdapter.updateItemList(substepList);
        substepAdapter.notifyDataSetChanged();
    }

    // add step to database
    public void addStep(Substep substep){
        //Log.i("info", "in edit task, adding step: " + substep.getStep() + " to database");
        taskDatabase.substepDao().addSubstep(substep);
        updateSubstepList();
        substepAdapter.updateItemList(substepList);
        substepAdapter.notifyDataSetChanged();
    }


    // save task
    public void saveClicked(){
        //Toast.makeText(this, "Save clicked", Toast.LENGTH_SHORT).show();
        // get input
        String title = binding.textInputTitle.getText().toString();
        String importance;
        String dueDate = binding.textInputDueDate.getText().toString();
        String description = binding.textInputDescription.getText().toString();

        if (binding.radioButtonHigh.isChecked()){
            importance = "High";
        }
        else if (binding.radioButtonMid.isChecked()){
            importance = "Mid";
        }
        else if (binding.radioButtonLow.isChecked()){
            importance = "Low";
        }
        else {
            importance = "None";
        }

        // (re)create new Task object with same id as current task
        currentTask = new Task(currentTaskID, title, importance, dueDate, description);
        Log.i("myInfo", "created task " + currentTask.getTitle());
        if (editingTask) {
            mainActivity.updateTask(currentTask);
        }
        else {
            mainActivity.addTask(currentTask);
        }
        dismiss();
    }


    // clear layout
    public void clearClicked(){
        binding.textInputTitle.setText("");
        binding.radioButtonHigh.setChecked(false);
        binding.radioButtonMid.setChecked(false);
        binding.radioButtonLow.setChecked(false);
        binding.textInputDueDate.setText("");
        binding.textInputDescription.setText("");
        Substep step;
        for (int i=0; i<substepList.length; i++){
            step = substepList[i];
            taskDatabase.substepDao().deleteSubstep(step);
        }
        substepList = new Substep[0];
        Log.i("info", "substep list length " + substepList.length);
        substepAdapter.updateItemList(substepList);
        substepAdapter.notifyDataSetChanged();

    }

    // populate existing task details if editing a Task
    public void setTask() {
        String importanceToSet = currentTask.getImportance();
        binding.textInputTitle.setText(currentTask.getTitle());
        binding.textInputDueDate.setText(currentTask.getDueDate());
        binding.textInputDescription.setText(currentTask.getDescription());

        if (Objects.equals(importanceToSet, "High")) {
            binding.radioButtonHigh.setChecked(true);
        } else if (Objects.equals(importanceToSet, "Mid")) {
            binding.radioButtonMid.setChecked(true);
        } else if (Objects.equals(importanceToSet, "Low")) {
            binding.radioButtonLow.setChecked(true);
        }

        substepList = taskDatabase.substepDao().getTaskSubsteps(currentTaskID);
        if (substepList != null) {
            substepAdapter.updateItemList(substepList);
        }
    }
}