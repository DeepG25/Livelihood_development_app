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

public class EN_food_security extends AppCompatActivity {

    EditText if_yes_borrow_cereal;
    Spinner own_farm,rice_wheat,borrow_cereal,reason_borrow_cereal,sufficient_food,additional_source,vegetable_garden,undernourished_child;
    Button next;
    Toolbar toolbar;
    TextView enroll_no;
    enroll en;

    private DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_food_security);

        toolbar = (Toolbar)findViewById(R.id.toolbar_efood_security);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Food Security");

        if_yes_borrow_cereal = (EditText)findViewById(R.id.if_yes_borrow_cereal_et);
        own_farm = (Spinner)findViewById(R.id.own_farm_spinner);
        rice_wheat = (Spinner)findViewById(R.id.rice_wheat_spinner);
        borrow_cereal = (Spinner)findViewById(R.id.borrow_cereal_spinner);
        reason_borrow_cereal = (Spinner)findViewById(R.id.reason_borrow_cereal_spinner);
        sufficient_food = (Spinner)findViewById(R.id.sufficient_food_spinner);
        additional_source = (Spinner)findViewById(R.id.additional_source_spinner);
        vegetable_garden = (Spinner)findViewById(R.id.vegetable_garden_spinner);
        undernourished_child = (Spinner)findViewById(R.id.undernourished_child_spinner);
        next = (Button)findViewById(R.id.next8_btn);

        enroll_no = (TextView)findViewById(R.id.enrollment_id_number6);
        enroll_no.setText(en.getEnroll_id());

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ifyes_borrow_cereal = if_yes_borrow_cereal.getText().toString();
                final String ownfarm = own_farm.getSelectedItem().toString();
                final String ricewheat = rice_wheat.getSelectedItem().toString();
                final String borrowcereal = borrow_cereal.getSelectedItem().toString();
                final String reason_borrowcereal = reason_borrow_cereal.getSelectedItem().toString();
                final String sufficientfood = sufficient_food.getSelectedItem().toString();
                final String additionalsource = additional_source.getSelectedItem().toString();
                final String vegetablegarden = vegetable_garden.getSelectedItem().toString();
                final String undernourishedchild = undernourished_child.getSelectedItem().toString();

                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = current_user.getUid();

                db = FirebaseDatabase.getInstance().getReference().child("Livelihood Programme").child(uid);
                Map<String ,Object> usermap = new HashMap<>();
                usermap.put("070:Food gained from own farm is enough for",ownfarm);
                usermap.put("071:Do you get Rice or Wheat from PDS",ricewheat);
                usermap.put("072:Was there any part when you borrowed cereals from others",borrowcereal);
                usermap.put("073:If YES How much quantity (Qtl)",ifyes_borrow_cereal);
                usermap.put("074:Reason of borrowing of cereals",reason_borrowcereal);
                usermap.put("075:Do you get sufficient food from your own farm",sufficientfood);
                usermap.put("076:What are the additional source from where you ensure food security",additionalsource);
                usermap.put("077:Whether vegetables in kitchen garden are grown round the year",vegetablegarden);
                usermap.put("078:Undernourished child in family",undernourishedchild);
                db.updateChildren(usermap);

                Toast.makeText(EN_food_security.this,"Saved",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(EN_food_security.this,EN_social_security_scheme.class);
                startActivity(i);
            }
        });
    }
}
