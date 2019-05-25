package com.example.dell.chaitanya;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EN_ds_business extends AppCompatActivity {

    EditText staff_members,product_no,footfall,revenue,profits;
    Spinner prod_expa;
    Button next;
    ProgressDialog progressDialog;
    Toolbar toolbar;

    private DatabaseReference db_ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_ds_business);
        progressDialog = new ProgressDialog(EN_ds_business.this);
        toolbar = (Toolbar)findViewById(R.id.toolbar_ds_buisness);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Business Information");

        staff_members = (EditText)findViewById(R.id.staff_members_et_ds);
        product_no = (EditText)findViewById(R.id.product_no_et_ds);
        footfall = (EditText)findViewById(R.id.footfall_et_ds);
        revenue = (EditText)findViewById(R.id.revenue_et_ds);
        profits = (EditText)findViewById(R.id.profits_et_ds);
        prod_expa = (Spinner) findViewById(R.id.prod_expa_spinner_ds);
        next = (Button)findViewById(R.id.next6_btn_ds);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String staffmembers = staff_members.getText().toString();
                final String productno = product_no.getText().toString();
                final String footfall_ = footfall.getText().toString();
                final String revenue_ = revenue.getText().toString();
                final String profits_ = profits.getText().toString();
                final String prodexpa = prod_expa.getSelectedItem().toString();

                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = current_user.getUid();

                db_ds = FirebaseDatabase.getInstance().getReference().child("Digital Sakhi").child(uid);
                Map<String ,Object> usermap = new HashMap<>();
                usermap.put("57:How many staff members do you have?",staffmembers);
                usermap.put("58:How many products or services do you offer",productno);
                usermap.put("59:Have you expanded your business in last 3 months(Diversity of products or services or size of business)?",prodexpa);
                usermap.put("60:What is the current footfall or customer base per day?",footfall_);
                usermap.put("61:What is the current revenue generated from your business?",revenue_);
                usermap.put("62:What are your current profits from the business?",profits_);
                db_ds.updateChildren(usermap);

                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(EN_ds_business.this,Login.class);
                startActivity(i);
                finish();
            }
        });
    }
}
