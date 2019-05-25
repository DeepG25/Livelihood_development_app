package com.example.dell.chaitanya;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {

    Button login_reg;
    Button register_reg;
    EditText enrollid_reg,pass_reg,conpass_reg;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    enroll en;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progressDialog = new ProgressDialog(Register.this);

        mAuth = FirebaseAuth.getInstance();

        login_reg = (Button)findViewById(R.id.login_btn);
        register_reg = (Button)findViewById(R.id.register_btn);
        enrollid_reg = (EditText)findViewById(R.id.registerEmailET);
        pass_reg = (EditText)findViewById(R.id.registerPassET);
        conpass_reg = (EditText)findViewById(R.id.registerPassConET);

        login_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(Register.this,Login.class);
                startActivity(i3);
            }
        });

        register_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Register.this, "Register Button clicked", Toast.LENGTH_SHORT).show();
                String Email = enrollid_reg.getText().toString();
                final String Em = enrollid_reg.getText().toString();
                Email = Email + "@chaitanya.org";
                String Pass = pass_reg.getText().toString();
                String PassCon = conpass_reg.getText().toString();

                Toast.makeText(Register.this, "Register Button clicked", Toast.LENGTH_SHORT).show();
                if (Pass.equals(PassCon)){
                    progressDialog.setMessage("Signing in");
                    progressDialog.show();
                    mAuth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                progressDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                progressDialog.dismiss();
                                //en.setEnroll_id(Em);
                                Toast.makeText(Register.this, "Register Successful", Toast.LENGTH_SHORT).show();
                                Intent i2 = new Intent(Register.this,ProjectActivity.class);
                                startActivity(i2);
                                finish();
                            }
                            else{
                                progressDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                progressDialog.dismiss();
                                Toast.makeText(Register.this, "Registration Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(Register.this, "Passwords Don't Match", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
