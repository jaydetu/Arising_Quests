package edu.ucdenver.questingcrew.arisingquests;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import edu.ucdenver.questingcrew.arisingquests.databinding.ActivityMainBinding;

public class EditTaskActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(R.layout.activity_edit_task);
    }

    /*
    // Get input text
    val inputText = filledTextField.editText?.text.toString()

    filledTextField.editText?.doOnTextChanged { inputText, _, _, _ ->
        // Respond to input text change
    }


    public void saveClicked(View view){
        // get input
        String title = binding.filledTextFieldTitle.editText?.text.toString();
        String importance;
        String dueDate;
        String description;

        if (binding.radio_button_high.isChecked()){

        }

        // create new Task object
        Task task = new Task(title, importance, dueDate, description);
    }


     */
    public void save(){

    }

    public void clearClicked(View view){

    }

}