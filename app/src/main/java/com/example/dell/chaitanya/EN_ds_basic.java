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
import java.util.Map;

public class EN_ds_basic extends AppCompatActivity {

    EditText name,phone,village,surveyor,date;
    Button next;

    Toolbar toolbar;
    private DatabaseReference db_ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_ds_basic);

        toolbar = (Toolbar)findViewById(R.id.toolbar_ds_basic);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Basic Information");

        name = (EditText) findViewById(R.id.name_et_ds);
        phone = (EditText) findViewById(R.id.phone_et_ds);
        village = (EditText) findViewById(R.id.village_et_ds);
        surveyor = (EditText) findViewById(R.id.surveyor_et_ds);
        date = (EditText) findViewById(R.id.date_et_ds);
        next = (Button)findViewById(R.id.next_btn_ds);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name_ = name.getText().toString();
                final String phone_ = phone.getText().toString();
                final String village_ = village.getText().toString();
                final String surveyor_ = surveyor.getText().toString();
                final String date_ = date.getText().toString();

                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = current_user.getUid();

                db_ds = FirebaseDatabase.getInstance().getReference().child("Digital Sakhi").child(uid);
                Map<String ,Object> usermap = new HashMap<>();
                usermap.put("01:Name of Women",name_);
                usermap.put("02:Phone number of women",phone_);
                usermap.put("03:Village Name",village_);
                usermap.put("04:Name of Surveyor",surveyor_);
                usermap.put("05:Date",date_);
                db_ds.setValue(usermap);

                Toast.makeText(EN_ds_basic.this,"Saved",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(EN_ds_basic.this,EN_ds_brand_awarness.class);
                startActivity(i);
            }
        });
    }
}
