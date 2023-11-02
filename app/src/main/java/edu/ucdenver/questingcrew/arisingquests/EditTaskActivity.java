package edu.ucdenver.questingcrew.arisingquests;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import edu.ucdenver.questingcrew.arisingquests.databinding.ActivityEditTaskBinding;

public class EditTaskActivity extends AppCompatActivity {

    private ActivityEditTaskBinding binding;
    private ArrayList<Substep> substepList;
    private SubstepsAdapter substepAdapter;

    @Override
    //protected void onCreate(Bundle savedInstanceState) {
    public void onCreate(Bundle savedInstanceState) {
    //public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("info", "start on create edit task");
        super.onCreate(savedInstanceState);
        binding = ActivityEditTaskBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.editTaskToolbar.inflateMenu(R.menu.menu_edit_task);

        binding.editTaskToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.action_cancel){
                    clearClicked(view);
                    exitTask(view);
                }
                else if (id == R.id.action_save){
                    saveClicked(view);
                    exitTask(view);
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
                saveClicked(view);
            }
        });
        binding.elevatedButtonCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                clearClicked(view);
            }
        });



        substepList = new ArrayList<Substep>();

        substepAdapter = new SubstepsAdapter(this, substepList);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        RecyclerView substepRecycler = binding.substepView.stepRecycler;
        substepRecycler.setLayoutManager(layoutManager);
        //substepRecycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        substepRecycler.setAdapter(substepAdapter);

    }


    // create and show substep dialog fragment
    public void addSubstep(View view){
        FragmentManager manager = getSupportFragmentManager();
        SubstepDialog substepsDialog = new SubstepDialog();
        Log.i("info", "create substep dialog");
        substepsDialog.show(manager, "");
        Log.i("info", "launch dialog");
    }



    //
    public void addStep(Substep substep){
        Log.i("info", "added step");
        substepList.add(substep);
        substepAdapter.notifyDataSetChanged();
    }


    public void saveClicked(View view){
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

        // create new Task object
        Task task = new Task(title, importance, dueDate, description);
    }



    public void save(){

    }

    // clar layout

    public void clearClicked(View view){
        binding.textInputTitle.setText("");
        binding.radioButtonHigh.setChecked(false);
        binding.radioButtonMid.setChecked(false);
        binding.radioButtonLow.setChecked(false);
        binding.textInputDueDate.setText("");
        binding.textInputDescription.setText("");
    }

    public void exitTask(View view){
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }

}