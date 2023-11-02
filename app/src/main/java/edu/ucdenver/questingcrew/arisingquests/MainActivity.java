package edu.ucdenver.questingcrew.arisingquests;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
        setSupportActionBar(binding.toolbarMain);
    }


    public void goToEditTask(View view){
        Intent editTaskActivity = new Intent(this, EditTaskActivity.class);
        startActivity(editTaskActivity);
    }

    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        // go to edit task activity
        if (id == R.id.action_add) {
            Intent editTaskActivity = new Intent(this, EditTaskActivity.class);
            startActivity(editTaskActivity);
        }

        return super.onOptionsItemSelected(item);
    }
}