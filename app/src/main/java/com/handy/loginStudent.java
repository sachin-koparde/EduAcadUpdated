package com.handy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginStudent extends AppCompatActivity {

    EditText loginUSN, loginPass;
    public String passIt;
    TextView message,link;
    Button loginButton, registerButton;
    SharedPreferences sp;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_student);

        loginUSN = (EditText)findViewById(R.id.loginUSNEditText);
        loginPass = (EditText)findViewById(R.id.loginPassEditText);

        message = (TextView)findViewById(R.id.InvalidTextView);

        loginButton = (Button) findViewById(R.id.buttonLogin);

        link = (TextView)findViewById(R.id.textViewLink);

        databaseReference = FirebaseDatabase.getInstance().getReference("EduAcad_Database").child("stuInfo");

        sp = getSharedPreferences("register",MODE_PRIVATE);

        if(sp.getBoolean("logged",false)){
            MainActivity();
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginStudent();
            }
        });

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterStudent();            }
        });
    }

    public void MainActivity()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public String password,usn;
    private void LoginStudent() {

        usn = loginUSN.getText().toString();
        passIt = usn;
        password = loginPass.getText().toString();

        if(usn.equals("") || password.equals(""))
        {
            message.setText("Enter all the credentials");
        }else {

                databaseReference.child(usn).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        post post = dataSnapshot.getValue(post.class);
                        try{
                        if (password.equals(post.getPassword())) {
                            Toast.makeText(loginStudent.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                            sp.edit().putBoolean("logged", true);

                            Intent intent = new Intent(loginStudent.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(loginStudent.this, "Enter a valid USN or Password", Toast.LENGTH_SHORT).show();
                            message.setText("Invalid USN or Password");

                        }
                            }catch (NullPointerException e){
                            Toast.makeText(loginStudent.this, "USN is not registered!", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();

                        }
                }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(loginStudent.this, "Failed to read value!", Toast.LENGTH_SHORT).show();
                    }
                });

        }




    }

    private void RegisterStudent() {
        Intent intent = new Intent(this,Registration.class);
        startActivity(intent);
        finish();
    }

}
