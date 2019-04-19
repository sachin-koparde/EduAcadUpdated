package com.handy;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class popup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        NextActivity();
    }

    private void NextActivity() {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent intent = new Intent(popup.this,loginStudent.class);
                popup.this.startActivity(intent);
                popup.this.finish();
            }
        }, 2000);
    }
}
