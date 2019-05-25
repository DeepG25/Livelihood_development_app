package com.example.dell.chaitanya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class LivestockActivity extends AppCompatActivity {

    EditText local_cow,buffalo,goat,sheep,backyard_poultry,bullock,milk_sold,eggs_sold,male_goat_sold,buffalo_sold,female_goat_sold,poultry_birds_sold;
    Spinner own_animal;
    Button next;
    TextView enroll_no;
    enroll en;
    Toolbar toolbar;

    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livestock);

        toolbar = (Toolbar)findViewById(R.id.toolbar_elivestock);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Livestock and Small Ruminants");

        local_cow = (EditText)findViewById(R.id.local_cow_et);
        buffalo = (EditText)findViewById(R.id.buffalo_et);
        goat = (EditText)findViewById(R.id.goat_et);
        sheep = (EditText)findViewById(R.id.sheep_et);
        backyard_poultry = (EditText)findViewById(R.id.backyard_poultry_et);
        bullock = (EditText)findViewById(R.id.bullock_et);
        milk_sold = (EditText)findViewById(R.id.milk_sold_et);
        eggs_sold = (EditText)findViewById(R.id.eggs_sold_et);
        male_goat_sold = (EditText)findViewById(R.id.male_goat_sold_et);
        buffalo_sold = (EditText)findViewById(R.id.buffalo_sold_et);
        female_goat_sold = (EditText)findViewById(R.id.female_goat_sold_et);
        poultry_birds_sold = (EditText)findViewById(R.id.poultry_birds_sold_et);
        own_animal = (Spinner)findViewById(R.id.own_animal_spinner);
        next = (Button)findViewById(R.id.next4_btn);

        enroll_no = (TextView)findViewById(R.id.enrollment_id_number2);
        enroll_no.setText(en.getEnroll_id());

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String cow = local_cow.getText().toString();
                final String buffalo_ = buffalo.getText().toString();
                final String goat_ = goat.getText().toString();
                final String sheep_ = sheep.getText().toString();
                final String backyardpoultry = backyard_poultry.getText().toString();
                final String bullock_ = bullock.getText().toString();
                final String milksold = milk_sold.getText().toString();
                final String eggssold = eggs_sold.getText().toString();
                final String male_goatsold = male_goat_sold.getText().toString();
                final String buffalosold = buffalo_sold.getText().toString();
                final String female_goatsold = female_goat_sold.getText().toString();
                final String poultry_birdssold = poultry_birds_sold.getText().toString();
                final String ownanimal = own_animal.getSelectedItem().toString();

                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                assert current_user != null;
                String uid = current_user.getUid();
                db = FirebaseDatabase.getInstance().getReference().child("Livelihood Programme").child(uid);

                Map<String ,Object> usermap = new HashMap<>();
                usermap.put("039:Do you have own animals",ownanimal);
                usermap.put("040:Cow",cow);
                usermap.put("041:Buffalo",buffalo_);
                usermap.put("042:Goat",goat_);
                usermap.put("043:Sheep",sheep_);
                usermap.put("044:Backyard Poultry",backyardpoultry);
                usermap.put("045:Bullock",bullock_);
                usermap.put("046:Milk sold in last year (Ltrs)",milksold);
                usermap.put("047:Eggs sold in last year (No's)",eggssold);
                usermap.put("048:Male goat sold in last year (No's)",male_goatsold);
                usermap.put("049:Buffalo sold in last year (No's)",buffalosold);
                usermap.put("050:Female goat sold in last year (No's)",female_goatsold);
                usermap.put("051:Poultry birds sold in last year (No's)",poultry_birdssold);
                db.updateChildren(usermap);

                Toast.makeText(LivestockActivity.this,"Saved",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(LivestockActivity.this,EN_FamilyIncome.class);
                startActivity(i);

            }
        });
    }
}
