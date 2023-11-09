package edu.ucdenver.questingcrew.arisingquests;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.ArrayList;

import edu.ucdenver.questingcrew.arisingquests.databinding.ActivityEditTaskBinding;

public class EditTaskActivity extends AppCompatActivity {

    private ActivityEditTaskBinding binding;
    private ArrayList<Substep> substepList;
    private SubstepsAdapter substepAdapter;

    @Override
    //protected void onCreate(Bundle savedInstanceState) {
    public void onCreate(Bundle savedInstanceState) {
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

        // Add mask to textInputDueDate to view input as MM/DD/YYYY
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



        substepList = new ArrayList<>();

        substepAdapter = new SubstepsAdapter(this, substepList);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        binding.content.recyclerView.setLayoutManager(layoutManager);
        //binding.content.recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        binding.content.recyclerView.setAdapter(substepAdapter);

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
        //substepList.add(substep);
        //substepAdapter.notifyDataSetChanged();
        //binding.content.recyclerView.invalidate();
        substepAdapter.addItem(substep);
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
        Toast.makeText(this, "You clicked on Save Contact Button", Toast.LENGTH_LONG).show();
    }



    // clear layout

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