package com.example.dell.chaitanya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EN_source_of_credits extends AppCompatActivity {

    EditText loan;
    Spinner loan_purpose;
    CheckBox shg,bank,farmer,coop,micro,other,no;
    Button next;
    Toolbar toolbar;
    TextView enroll_no;
    enroll en;

    private DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_source_of_credits);

        toolbar = (Toolbar)findViewById(R.id.toolbar_esource);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Source of Credits");

        loan = (EditText)findViewById(R.id.loan_et);
        loan_purpose = (Spinner)findViewById(R.id.loan_purpose_spinner);
        shg = (CheckBox)findViewById(R.id.shg_box);
        bank = (CheckBox)findViewById(R.id.bank_box);
        farmer = (CheckBox)findViewById(R.id.farmer_box);
        coop = (CheckBox)findViewById(R.id.cooperative_box);
        micro = (CheckBox)findViewById(R.id.microfinanace_box);
        other = (CheckBox)findViewById(R.id.other_box);
        no = (CheckBox)findViewById(R.id.noloan_box);
        next = (Button)findViewById(R.id.next6_btn);

        enroll_no = (TextView)findViewById(R.id.enrollment_id_number4);
        enroll_no.setText(en.getEnroll_id());

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String loan_ = loan.getText().toString();
                final String loan_p = loan_purpose.getSelectedItem().toString();
                String s = "";
                if(shg.isChecked())
                {
                    s = s + "SHG,";
                }
                if(bank.isChecked())
                {
                    s = s + "Bank,";
                }
                if(farmer.isChecked())
                {
                    s = s + "Farmer Credit Card,";
                }
                if(coop.isChecked())
                {
                    s = s + "Cooperative Society,";
                }
                if(micro.isChecked())
                {
                    s = s + "Micro Finance,";
                }
                if(other.isChecked())
                {
                    s = s + "Other,";
                }
                if(no.isChecked())
                {
                    s = s + "No Loan,";
                }

                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                assert current_user != null;
                String uid = current_user.getUid();
                db = FirebaseDatabase.getInstance().getReference().child("Livelihood Programme").child(uid);

                Map<String ,Object> usermap = new HashMap<>();
                usermap.put("062:Credit Source",s);
                usermap.put("063:Loan taken last year",loan_);
                usermap.put("064:Purpose of Loan",loan_p);
                db.updateChildren(usermap);

                Intent i = new Intent(EN_source_of_credits.this,EN_migration.class);
                startActivity(i);
            }
        });
    }
}
