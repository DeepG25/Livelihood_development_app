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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EN_generalinfo extends AppCompatActivity {

    Toolbar toolbar;
    EditText district,taluka,village;
    Button next;

    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_generalinfo);

        district = (EditText)findViewById(R.id.district);
        taluka = (EditText)findViewById(R.id.taluka);
        village = (EditText)findViewById(R.id.village);

        next = (Button)findViewById(R.id.next_btn);
        toolbar = (Toolbar)findViewById(R.id.toolbar_egi);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("General Information");

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String District = district.getText().toString();
                final String Taluka = taluka.getText().toString();
                final String Village = village.getText().toString();

                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = current_user.getUid();

                db = FirebaseDatabase.getInstance().getReference().child("Livelihood Programme").child(uid);
                Map<String ,Object> usermap = new HashMap<>();
                usermap.put("001:District",District);
                usermap.put("002:Taluka",Taluka);
                usermap.put("003:Village",Village);
                db.setValue(usermap);

                Toast.makeText(EN_generalinfo.this,"Saved",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(EN_generalinfo.this,EN_personalinfo.class);
                startActivity(i);
            }
        });
    }
}
