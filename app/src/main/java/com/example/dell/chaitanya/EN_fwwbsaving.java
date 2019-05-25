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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EN_fwwbsaving extends AppCompatActivity {


    EditText rd,fd,post_office,shg,maroop,total_loans;
    Spinner do_sav,yes_where,do_rd,do_fd,have_loan,purps_loan,do_insurance;
    Button next_btn_1;
    TextView eid_saving;
    Toolbar toolbar;
    enroll en;
    private DatabaseReference db_fwwb;
    CheckBox check_mfi,check_cooprative,check_ngo,check_bank,check_lifeinsu,check_medi,check_live,check_crop,check_business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_fwwbsaving);
        toolbar = (Toolbar)findViewById(R.id.toolbar_fwwb_saving);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Savings and Insurance");
        rd = (EditText)findViewById(R.id.rd_inr_et_fwwb);
        fd = (EditText)findViewById(R.id.fd_inr_et_fwwb);
        post_office = (EditText)findViewById(R.id.post_office_inr_et_fwwb);
        shg = (EditText)findViewById(R.id.shg_inr_et_fwwb);
        maroop = (EditText)findViewById(R.id.maroop_inr_et_fwwb);
        total_loans = (EditText)findViewById(R.id.total_loan_et_fwwb);

        do_sav = (Spinner)findViewById(R.id.saving_account_spinner_fwwb);
        yes_where = (Spinner)findViewById(R.id.if_yes_where_spinner_fwwb);
        do_rd = (Spinner)findViewById(R.id.rd_spinner_fwwb);
        do_fd = (Spinner)findViewById(R.id.fd_spinner_fwwb);
        have_loan = (Spinner)findViewById(R.id.taken_loans_spinner_fwwb);
        purps_loan = (Spinner)findViewById(R.id.purpose_loan_spinner_fwwb);
        do_insurance = (Spinner)findViewById(R.id.do_insurance_spinner_fwwb);

        next_btn_1 = (Button)findViewById(R.id.next1_btn_fwwb);
        eid_saving = (TextView)findViewById(R.id.enrollment_id_number_fwwb_2);

        check_mfi = (CheckBox)findViewById(R.id.if_yes_source_checkbox_fwwb_1);
        check_cooprative = (CheckBox)findViewById(R.id.if_yes_source_checkbox_fwwb_2);
        check_ngo = (CheckBox)findViewById(R.id.if_yes_source_checkbox_fwwb_3);
        check_bank = (CheckBox)findViewById(R.id.if_yes_source_checkbox_fwwb_4);
        check_lifeinsu = (CheckBox)findViewById(R.id.if_yes_do_insurance_checkbox_fwwb_1);
        check_medi = (CheckBox)findViewById(R.id.if_yes_do_insurance_checkbox_fwwb_2);
        check_live = (CheckBox)findViewById(R.id.if_yes_do_insurance_checkbox_fwwb_3);
        check_crop = (CheckBox)findViewById(R.id.if_yes_do_insurance_checkbox_fwwb_4);
        check_business = (CheckBox)findViewById(R.id.if_yes_do_insurance_checkbox_fwwb_5);

        eid_saving.setText(en.getEnroll_id());

        next_btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rd_et_string = rd.getText().toString();
                String fd_et_string = fd.getText().toString();
                String post_office_et_string = post_office.getText().toString();
                String shg_et_string = shg.getText().toString();
                String maroop_et_string = maroop.getText().toString();
                String total_loans_et_string = total_loans.getText().toString();

                String do_sav_spinner = do_sav.getSelectedItem().toString();
                String yes_where_spinner = yes_where.getSelectedItem().toString();
                String do_rd_spinner = do_rd.getSelectedItem().toString();
                String do_fd_spinner = do_fd.getSelectedItem().toString();
                String have_loan_spinner = have_loan.getSelectedItem().toString();
                String purps_loan_spinner = purps_loan.getSelectedItem().toString();
                String do_insurance_spinner = do_insurance.getSelectedItem().toString();

                String Multiple_2_1 = "";
                if (check_mfi.isChecked())
                {
                    Multiple_2_1 += "MFI ";
                }
                if (check_cooprative.isChecked())
                {
                    Multiple_2_1 += "Cooprative ";
                }
                if (check_ngo.isChecked())
                {
                    Multiple_2_1 += "NGO ";
                }
                if (check_bank.isChecked())
                {
                    Multiple_2_1 += "Bank ";
                }
                String Multiple_2_2 = "";
                if (check_lifeinsu.isChecked())
                {
                    Multiple_2_2 += "Life Insurance ";
                }
                if (check_medi.isChecked())
                {
                    Multiple_2_2 += "Medical ";
                }
                if (check_live.isChecked())
                {
                    Multiple_2_2 += "Livestock ";
                }
                if (check_crop.isChecked())
                {
                    Multiple_2_2 += "Crop ";
                }
                if (check_business.isChecked())
                {
                    Multiple_2_2 += "Business";
                }

                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = current_user.getUid();

                db_fwwb = FirebaseDatabase.getInstance().getReference().child("FWWB Program").child(uid);
                Map<String, Object> usermap = new HashMap<>();
                usermap.put("23:Do you have saving accounts",do_sav_spinner);
                usermap.put("24:If YES, then where",yes_where_spinner);
                usermap.put("25:Do you have RD",do_rd_spinner);
                usermap.put("26:Do you have FD",do_fd_spinner);
                usermap.put("27:RD (INR)",rd_et_string);
                usermap.put("28:FD (INR)",fd_et_string);
                usermap.put("29:Post Office (INR)",post_office_et_string);
                usermap.put("30:SHG (INR)",shg_et_string);
                usermap.put("31:Maroop (INR)",maroop_et_string);
                usermap.put("32:Have you taken loan(s)",have_loan_spinner);
                usermap.put("33:If YES, which source",Multiple_2_1);            //CHECKBOX
                usermap.put("34:Purpose of Loan(s)",purps_loan_spinner);
                usermap.put("35:Total Amount of loan(s) (INR)",total_loans_et_string);
                usermap.put("36:Do you have insurance",do_insurance_spinner);
                usermap.put("37:If YES, type of Insurance",Multiple_2_2);       //CHECKBOX
                db_fwwb.updateChildren(usermap);

                Toast.makeText(EN_fwwbsaving.this,"Saved",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(EN_fwwbsaving.this,EN_fwwbdigital.class);
                startActivity(i);

            }
        });
    }
}
