package com.example.dell.chaitanya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EN_ds_financial extends AppCompatActivity {

    Spinner manages_money,day_to_day,expenses,consult;
    Button next;
    Toolbar toolbar;
    private DatabaseReference db_ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_ds_financial);

        toolbar = (Toolbar)findViewById(R.id.toolbar_ds_financial);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Financial Information");

        manages_money = (Spinner)findViewById(R.id.manages_money_spinner_ds);
        day_to_day = (Spinner)findViewById(R.id.day_to_day_spinner_ds);
        expenses = (Spinner)findViewById(R.id.expenses_spinner_ds);
        consult = (Spinner)findViewById(R.id.consult_spinner_ds);
        next = (Button)findViewById(R.id.next3_btn_ds);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String managesmoney = manages_money.getSelectedItem().toString();
                final String day_today = day_to_day.getSelectedItem().toString();
                final String expenses_ = expenses.getSelectedItem().toString();
                final String consult_ = consult.getSelectedItem().toString();

                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = current_user.getUid();

                db_ds = FirebaseDatabase.getInstance().getReference().child("Digital Sakhi").child(uid);
                Map<String ,Object> usermap = new HashMap<>();
                usermap.put("24:Who manages money in your Household?",managesmoney);
                usermap.put("25:Who is responsible for day to day decisions about money?",day_today);
                usermap.put("26:Do you know the various financial expenses of your household?",expenses_);
                usermap.put("27:Does you household consult you before making a financial decision or financial expense?",consult_);
                db_ds.updateChildren(usermap);

                Toast.makeText(EN_ds_financial.this,"Saved",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(EN_ds_financial.this,EN_ds_household_finance.class);
                startActivity(i);
            }
        });
    }
}
