package edu.ucdenver.questingcrew.arisingquests;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class mentalHealth extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mental_health);

        Button takelinkbtn = findViewById(R.id.take_this_btn);
        Button cardlinkbtn = findViewById(R.id.mentalHealthCardButton);
        Button lifelinkbtn = findViewById(R.id.lifeLineButton);

        takelinkbtn.setOnClickListener(view -> goLink("https://www.takethis.org"));

        cardlinkbtn.setOnClickListener(view -> goLink("https://mentalhealthhelp.carrd.co"));

        lifelinkbtn.setOnClickListener(view -> goLink("https://988lifeline.org"));

    }

    private void goLink(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}
