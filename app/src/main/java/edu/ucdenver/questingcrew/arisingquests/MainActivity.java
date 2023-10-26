package edu.ucdenver.questingcrew.arisingquests;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import edu.ucdenver.questingcrew.arisingquests.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void goToEditTask(View view){
        Log.i("info", "go to edit task");
        Intent editTaskActivity = new Intent(this, EditTaskActivity.class);
        Log.i("info", "intent create");
        startActivity(editTaskActivity);
        Log.i("info", "start activity");

    }
}