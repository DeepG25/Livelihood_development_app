package com.example.dell.chaitanya;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText enrollid,password;
    Button register;
    Button login;
    enroll en;


    ProgressDialog progressDialog;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null)
        {
            Intent i = new Intent(Login.this,ProjectActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(Login.this);

        mAuth = FirebaseAuth.getInstance();

        login = (Button)findViewById(R.id.loginButton);
        register = (Button)findViewById(R.id.registerButton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(Login.this,Register.class);
                startActivity(i2);
            }
        });

        enrollid = (EditText)findViewById(R.id.loginEditText);
        password = (EditText)findViewById(R.id.passwordEditText);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = enrollid.getText().toString();
                final String Em = enrollid.getText().toString();
                Email = Email + "@chaitanya.org";
                String Password = password.getText().toString();

                progressDialog.setMessage("Logging in");
                progressDialog.show();

                mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {

                            progressDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            progressDialog.dismiss();
                            //en.setEnroll_id(Em);
                            Toast.makeText(Login.this,"User Logged In",Toast.LENGTH_SHORT).show();
                            Intent i1 = new Intent(Login.this,ProjectActivity.class);
                            startActivity(i1);
                            finish();
                        }
                        else
                        {
                            progressDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            progressDialog.dismiss();
                            Toast.makeText(Login.this,"Invalid Username or Password",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
