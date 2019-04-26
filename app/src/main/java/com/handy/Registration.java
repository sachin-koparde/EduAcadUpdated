package com.handy;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
    Button button, buttonLogReg;
    EditText editTextName, editTextUSN, editTextBranch, editTextSem, editTextEmail,editTextPhone,editTextPass,editTextConPass;
    String pass,conPass;
    ImageView imageView;
    private post post;
    static int PReqCode = 1;
    static int REQUESTCODE = 1;
    int temp =0;
    Uri pickedImgUri;
    //Firebase
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    //Preferences
    public SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        button = (Button) findViewById(R.id.buttonSubmit);
        editTextName =(EditText)findViewById(R.id.nameEditText);
        editTextUSN =(EditText)findViewById(R.id.USNEditText);
        editTextBranch =(EditText)findViewById(R.id.BranchEditText);
        editTextSem =(EditText)findViewById(R.id.SemEditText);
        editTextEmail = (EditText)findViewById(R.id.EmailEditText);
        editTextPhone = (EditText)findViewById(R.id.phoneEditText);
        editTextPass = (EditText)findViewById(R.id.passEditText);
        editTextConPass = (EditText)findViewById(R.id.conPassEditText);
        imageView = (ImageView) findViewById(R.id.proPicImage);






        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= 22)
                {
                    checkAndRequestPermission();
                }
                else
                {
                    openGallery();
                }
            }
        });

        sp = getSharedPreferences("register",MODE_PRIVATE);

        if(sp.getBoolean("logged",false)){
            MainActivity();
        }

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("EduAcad_Database").child("stuInfo");



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editTextName.getText())) {
                    editTextName.setError("Name required!");
                } else if (TextUtils.isEmpty(editTextUSN.getText())) {
                    editTextUSN.setError("USN required!");
                } else if (TextUtils.isEmpty(editTextBranch.getText())) {
                    editTextBranch.setError("USN required!");
                } else if(TextUtils.isEmpty(editTextSem.getText())) {
                    editTextSem.setError("USN required!");
                } else if(TextUtils.isEmpty(editTextEmail.getText())){
                    editTextEmail.setError("Email required!");
                }else if(validate()){
                    Toast.makeText(Registration.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                    temp = 1;

                } else {
                    editTextConPass.setError("Password mismatch");
                }
                if(temp==1){
                    postComment();
                    MainActivity();
                    sp.edit().putBoolean("logged", true).apply();
                }
            }
        });


    }

    private void Login() {
        Intent i = new Intent(this,loginStudent.class);
        startActivity(i);
        finish();
    }

    public Boolean validate(){
        pass = editTextPass.getText().toString();
        conPass = editTextConPass.getText().toString();
        if(pass.equals(conPass)){
            return true;
        }else{
            Toast.makeText(Registration.this,"Enter a matching password",Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private void openGallery() {

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESTCODE);
    }

    private void checkAndRequestPermission()
    {
        if(ContextCompat.checkSelfPermission(Registration.this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(Registration.this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                Toast.makeText(Registration.this,"Accept for required permission",Toast.LENGTH_SHORT).show();

            }
            else
            {
                ActivityCompat.requestPermissions(Registration.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PReqCode);
            }
        }
        else
        {
            openGallery();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== RESULT_OK && requestCode == REQUESTCODE && data != null)
        {
            pickedImgUri = data.getData();
            imageView.setImageURI(pickedImgUri);
        }
    }

    public void MainActivity()
    {
        Intent intent = new Intent(this,loginStudent.class);
        startActivity(intent);
        finish();
    }

    public void Registration() {
        Intent intent = new Intent(this,Registration.class);
        startActivity(intent);
    }

    private void postComment() {
        String name = editTextName.getText().toString();
        String usn = editTextUSN.getText().toString();
        String branch = editTextBranch.getText().toString();
        String sem = editTextSem.getText().toString();
        String password = editTextPass.getText().toString();


        post post = new post(name,usn,branch,sem,password);

        databaseReference.child(post.getUsn()).setValue(post);


    }

}