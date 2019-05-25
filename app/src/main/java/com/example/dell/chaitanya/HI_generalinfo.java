package com.example.dell.chaitanya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class HI_generalinfo extends AppCompatActivity {

    Toolbar toolbar;
    EditText district,taluka,village;
    Button next;

    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hi_generalinfo);

        toolbar = (Toolbar)findViewById(R.id.toolbar_hgi);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("सामान्य जानकारी");

        district = (EditText)findViewById(R.id.district1);
        taluka = (EditText)findViewById(R.id.taluka1);
        village = (EditText)findViewById(R.id.village1);
        next = (Button)findViewById(R.id.next_btn1);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String District = district.getText().toString();
                final String Taluka = taluka.getText().toString();
                final String Village = village.getText().toString();

                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = current_user.getUid();

                db = FirebaseDatabase.getInstance().getReference().child("Womens").child(uid);
                HashMap<String ,String> usermap = new HashMap<>();
                usermap.put("01:District",District);
                usermap.put("02:Taluka",Taluka);
                usermap.put("03:Village",Village);
                db.setValue(usermap);

                Toast.makeText(HI_generalinfo.this,"Saved",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(HI_generalinfo.this,HI_personalinfo.class);
                startActivity(i);
            }
        });
    }
}
