package com.example.dell.chaitanya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EN_fwwbdigital extends AppCompatActivity {

    Spinner atm_debit_spinner_fwwb_java,if_yes_atm_debit_spinner_fwwb_java,if_no_atm_debit_spinner_fwwb_java,if_no_atm_debit_reason_spinner_fwwb_java,digital_banking_spinner_fwwb_java,if_no_reason_spinner_fwwb_java;
    Button next_btn_2;
    TextView eid_digital;
    Toolbar toolbar;
    enroll en;
    CheckBox check_bhim,check_bapp,check_micro,check_pos;
    private DatabaseReference db_fwwb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_fwwbdigital);
        toolbar = (Toolbar)findViewById(R.id.toolbar_fwwb_digital);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Digital Banking Service");
        atm_debit_spinner_fwwb_java = (Spinner)findViewById(R.id.atm_debit_spinner_fwwb);
        if_yes_atm_debit_spinner_fwwb_java = (Spinner)findViewById(R.id.if_yes_atm_debit_spinner_fwwb);
        if_no_atm_debit_spinner_fwwb_java = (Spinner)findViewById(R.id.if_no_atm_debit_spinner_fwwb);
        if_no_atm_debit_reason_spinner_fwwb_java = (Spinner)findViewById(R.id.if_no_atm_debit_reason_spinner_fwwb);
        digital_banking_spinner_fwwb_java = (Spinner)findViewById(R.id.digital_banking_spinner_fwwb);
        if_no_reason_spinner_fwwb_java = (Spinner)findViewById(R.id.if_no_reason_spinner_fwwb);

        next_btn_2 = (Button)findViewById(R.id.next2_btn_fwwb);
        eid_digital = (TextView)findViewById(R.id.enrollment_id_number_fwwb_3);

        check_bhim = (CheckBox)findViewById(R.id.if_yes_digital_banking_checkbox_fwwb_1);
        check_bapp = (CheckBox)findViewById(R.id.if_yes_digital_banking_checkbox_fwwb_2);
        check_micro = (CheckBox)findViewById(R.id.if_yes_digital_banking_checkbox_fwwb_3);
        check_pos = (CheckBox)findViewById(R.id.if_yes_digital_banking_checkbox_fwwb_4);

        eid_digital.setText(en.getEnroll_id());

        next_btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String atm_debit_spinner_fwwb_string = atm_debit_spinner_fwwb_java.getSelectedItem().toString();
                String if_yes_atm_debit_spinner_fwwb_string = if_yes_atm_debit_spinner_fwwb_java.getSelectedItem().toString();
                String if_no_atm_debit_spinner_fwwb_string = if_no_atm_debit_spinner_fwwb_java.getSelectedItem().toString();
                String if_no_atm_debit_reason_spinner_fwwb_string = if_no_atm_debit_reason_spinner_fwwb_java.getSelectedItem().toString();
                String digital_banking_spinner_fwwb_string = digital_banking_spinner_fwwb_java.getSelectedItem().toString();
                String if_no_reason_spinner_fwwb_string = if_no_reason_spinner_fwwb_java.getSelectedItem().toString();

                String Multiple_3 = "";
                if (check_bhim.isChecked())
                {
                    Multiple_3 += "BHIM App ";
                }
                if (check_bapp.isChecked())
                {
                    Multiple_3 += "Bank App ";
                }
                if (check_micro.isChecked())
                {
                    Multiple_3 += "Micro ATM ";
                }
                if (check_pos.isChecked())
                {
                    Multiple_3 += "POS Machine";
                }

                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = current_user.getUid();

                db_fwwb = FirebaseDatabase.getInstance().getReference().child("FWWB Program").child(uid);
                Map<String, Object> usermap = new HashMap<>();
                usermap.put("38:Do you have ATM or Debit card",atm_debit_spinner_fwwb_string);
                usermap.put("39:If yes, do you operate it",if_yes_atm_debit_spinner_fwwb_string);
                usermap.put("40:If No, who operates it",if_no_atm_debit_spinner_fwwb_string);
                usermap.put("41:Reason for not using",if_no_atm_debit_reason_spinner_fwwb_string);
                usermap.put("42:Do you use digital banking",digital_banking_spinner_fwwb_string);
                usermap.put("43:If yes, which digital platform you are currently using",Multiple_3);      //CHECKBOX
                usermap.put("44:If No, reason for not using",if_no_reason_spinner_fwwb_string);
                db_fwwb.updateChildren(usermap);

                Toast.makeText(EN_fwwbdigital.this,"Saved",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(EN_fwwbdigital.this,EN_fwwbenterprise.class);
                startActivity(i);
            }
        });

    }
}
