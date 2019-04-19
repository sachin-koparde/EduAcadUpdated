package com.handy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Profile extends Fragment {
    Button button;
    TextView proname,usn,branch,sem;
    AppCompatActivity appCompatActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_profile, container, false);
        button = (Button) view.findViewById(R.id.buttonLogout);

        proname = (TextView) view.findViewById(R.id.proName);
        usn = (TextView) view.findViewById(R.id.proUSN);
        branch = (TextView)view.findViewById(R.id.proBranch);
        sem = (TextView)view.findViewById(R.id.proSem);

        loginStudent l = new loginStudent();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });
        return view;



    }

    public void Logout(){
        SharedPreferences sp = getActivity().getSharedPreferences("register", 0);
        SharedPreferences.Editor edit = sp.edit();
        edit.clear();
        edit.apply();
        Intent i = new Intent(Profile.this.getContext(),loginStudent.class);
        startActivity(i);
        getActivity().finish();
    }
}
