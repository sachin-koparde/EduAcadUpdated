package com.handy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Register_option extends AppCompatActivity {

    Button buttonStaff, buttonStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_option);

        buttonStaff = (Button)findViewById(R.id.buttonRegStaff);
        buttonStudent = (Button)findViewById(R.id.buttonRegStudent);

        buttonStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegStaff();
            }
        });

        buttonStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegStudent();
            }
        });
    }

    private void RegStaff() {
        Intent intent = new Intent();
    }

    private void RegStudent()
    {
        Intent intent = new Intent(this,Registration.class);
        startActivity(intent);
    }
}