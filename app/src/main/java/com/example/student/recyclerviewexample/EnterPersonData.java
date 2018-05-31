package com.example.student.recyclerviewexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class EnterPersonData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_person_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateEverything();
            }
        });
    }

    private void calculateEverything() {
        EditText nameEditText = findViewById(R.id.nameField);
        EditText surnameEditText = findViewById(R.id.surnameField);
        String name = nameEditText.getText().toString();
        String surName = surnameEditText.getText().toString();
        Intent resIntent = new Intent();
        resIntent.putExtra("name", name);
        resIntent.putExtra("surname", surName);
        setResult(RESULT_OK, resIntent);
        finish();
    }

}
