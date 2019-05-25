package com.example.dell.chaitanya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class EN_FamilyIncome extends AppCompatActivity {

    EditText agriculture,animal_husbandry,ntfp_sale,local_labour,migrant_labour,service,enterprises,govt_pension,other_income,total_income;
    Toolbar toolbar;
    Button next;
    TextView enroll_no;
    enroll en;

    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en__family_income);

        toolbar = (Toolbar)findViewById(R.id.toolbar_efamily_income);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Family Income");

        agriculture = (EditText)findViewById(R.id.agriculture_et);
        animal_husbandry = (EditText)findViewById(R.id.animal_husbandry_et);
        ntfp_sale = (EditText)findViewById(R.id.ntfp_sale_et);
        local_labour = (EditText)findViewById(R.id.local_labour_et);
        migrant_labour = (EditText)findViewById(R.id.migrant_labour_et);
        service = (EditText)findViewById(R.id.service_et);
        enterprises = (EditText)findViewById(R.id.enterprises_et);
        govt_pension = (EditText)findViewById(R.id.govt_pension_et);
        other_income = (EditText)findViewById(R.id.other_income_et);
        total_income = (EditText)findViewById(R.id.total_income_et);
        next = (Button)findViewById(R.id.next5_btn);

        enroll_no = (TextView)findViewById(R.id.enrollment_id_number3);
        enroll_no.setText(en.getEnroll_id());

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String agriculture_ = agriculture.getText().toString();
                final String animal_husbandry_ = animal_husbandry.getText().toString();
                final String ntfp_sale_ = ntfp_sale.getText().toString();
                final String local_labour_ = local_labour.getText().toString();
                final String migrant_labour_ = migrant_labour.getText().toString();
                final String service_ = service.getText().toString();
                final String enterprises_ = enterprises.getText().toString();
                final String govt_pension_ = govt_pension.getText().toString();
                final String other_income_ = other_income.getText().toString();
                final String total_income_ = total_income.getText().toString();

                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = current_user.getUid();

                db = FirebaseDatabase.getInstance().getReference().child("Livelihood Programme").child(uid);
                Map<String ,Object> usermap = new HashMap<>();
                usermap.put("052:Income from Agriculture",agriculture_);
                usermap.put("053:Income from Animal Husbandry",animal_husbandry_);
                usermap.put("054:Income from NTFP Sale",ntfp_sale_);
                usermap.put("055:Income from Local Labour",local_labour_);
                usermap.put("056:Income from Migrant Labour",migrant_labour_);
                usermap.put("057:Income from Service",service_);
                usermap.put("058:Income from Enterprises",enterprises_);
                usermap.put("059:Income from Govt Pension",govt_pension_);
                usermap.put("060:Income from Other Income(Rent)",other_income_);
                usermap.put("061:Total Income",total_income_);
                db.updateChildren(usermap);

                Toast.makeText(EN_FamilyIncome.this,"Saved",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(EN_FamilyIncome.this,EN_source_of_credits.class);
                startActivity(i);
            }
        });
    }
}
