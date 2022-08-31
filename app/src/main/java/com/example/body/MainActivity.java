package com.example.body;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    TextView smellDescs;
    Button learnMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        String mainDescs = "The second activity is about bodyobject so, body is something that is useful and need by all human being!!";

        smellDescs = findViewById(R.id.smellDescs);
        learnMore = findViewById(R.id.learnMore);

        smellDescs.setText(mainDescs);
        learnMore.setOnClickListener(v-> {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        });
    }
}