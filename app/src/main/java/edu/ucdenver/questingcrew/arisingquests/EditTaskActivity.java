package edu.ucdenver.questingcrew.arisingquests;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.app.AlertDialog;
import android.app.Dialog;

import java.util.ArrayList;

import edu.ucdenver.questingcrew.arisingquests.databinding.ActivityEditTaskBinding;
import edu.ucdenver.questingcrew.arisingquests.databinding.ActivityMainBinding;
import edu.ucdenver.questingcrew.arisingquests.databinding.DialogSubstepBinding;

public class EditTaskActivity extends AppCompatActivity {

    private ActivityEditTaskBinding binding;
    private ArrayList<Substep> substepList;
    private SubstepsAdapter substepAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("info", "start on create edit task");
        super.onCreate(savedInstanceState);
        binding = ActivityEditTaskBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Log.i("info", "finished binding");

        substepList = new ArrayList<Substep>();

        substepAdapter = new SubstepsAdapter(this, substepList);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        RecyclerView substepRecycler = binding.substepRecyclerView;
        substepRecycler.setLayoutManager(layoutManager);
        substepRecycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        substepRecycler.setAdapter(substepAdapter);



    }

    /*
    // Get input text
    val inputText = filledTextField.editText?.text.toString()

    filledTextField.editText?.doOnTextChanged { inputText, _, _, _ ->
        // Respond to input text change
    }
     */


    public void addSubstep(View view){
        SubstepDialog substepsDialog = new SubstepDialog();
        substepsDialog.show(getSupportFragmentManager(), "");

    }

    public void addStep(Substep step){
        substepList.add(step);
        substepAdapter.notifyDataSetChanged();
    }


    public void saveClicked(View view){
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

}