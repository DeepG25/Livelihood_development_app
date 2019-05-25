package com.example.dell.chaitanya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EN_ds_loan extends AppCompatActivity {

    Spinner bank_account,account_type,bank_visit,reason_visit,open_account,financial_pr,financial_pr1,service_bank,service_bank1,loan_taken,loan_whom,keep_money,
            debt_text, credit_linkage,insu_pro,what_insu,saving_bank,pension;
    CheckBox ration_card,aadhaar_card,vao,voter_id,pan,other;
    Button next;
    Toolbar toolbar;
    private DatabaseReference db_ds;
    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_ds_loan);

        toolbar = (Toolbar)findViewById(R.id.toolbar_ds_loan);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Loan Related");

        bank_account = (Spinner)findViewById(R.id.bank_account_spinner_ds);
        account_type = (Spinner)findViewById(R.id.account_type_spinner_ds);
        bank_visit = (Spinner)findViewById(R.id.bank_visit_spinner_ds);
        reason_visit = (Spinner)findViewById(R.id.reason_visit_spinner_ds);
        open_account = (Spinner)findViewById(R.id.open_account_spinner_ds);
        financial_pr = (Spinner)findViewById(R.id.financial_pr_spinner_ds);
        financial_pr1 = (Spinner)findViewById(R.id.financial_pr1_spinner_ds);
        service_bank = (Spinner)findViewById(R.id.service_bank_spinner_ds);
        service_bank1 = (Spinner)findViewById(R.id.service_bank1_spinner_ds);
        loan_taken = (Spinner)findViewById(R.id.loan_taken_spinner_ds);
        loan_whom = (Spinner)findViewById(R.id.loan_whom_spinner_ds);
        keep_money = (Spinner)findViewById(R.id.keep_money_spinner_ds);
        debt_text = (Spinner)findViewById(R.id.debt_spinner_ds);
        credit_linkage = (Spinner)findViewById(R.id.credit_linkage_spinner_ds);
        insu_pro = (Spinner)findViewById(R.id.insu_pro_spinner_ds);
        what_insu = (Spinner)findViewById(R.id.what_insu_spinner_ds);
        saving_bank = (Spinner)findViewById(R.id.saving_bank_spinner_ds);
        pension = (Spinner)findViewById(R.id.pension_spinner_ds);
        ration_card = (CheckBox)findViewById(R.id.ration_card_box);
        aadhaar_card = (CheckBox)findViewById(R.id.aadhaar_card_box);
        vao = (CheckBox)findViewById(R.id.vao_box);
        voter_id = (CheckBox)findViewById(R.id.voter_id_box);
        pan = (CheckBox)findViewById(R.id.pan_box);
        other = (CheckBox)findViewById(R.id.other_box);
        next = (Button)findViewById(R.id.next5_btn_ds);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String bankaccount = bank_account.getSelectedItem().toString();
                final String accounttype = account_type.getSelectedItem().toString();
                final String bankvisit = bank_visit.getSelectedItem().toString();
                final String reasonvisit = reason_visit.getSelectedItem().toString();
                final String openaccount = open_account.getSelectedItem().toString();
                final String financialpr = financial_pr.getSelectedItem().toString();
                final String financialpr1 = financial_pr1.getSelectedItem().toString();
                final String servicebank = service_bank.getSelectedItem().toString();
                final String servicebank1 = service_bank1.getSelectedItem().toString();
                final String loantaken = loan_taken.getSelectedItem().toString();
                final String loanwhom = loan_whom.getSelectedItem().toString();
                final String keepmoney = keep_money.getSelectedItem().toString();
                final String debttext = debt_text.getSelectedItem().toString();
                final String creditlinkage = credit_linkage.getSelectedItem().toString();
                final String insupro = insu_pro.getSelectedItem().toString();
                final String whatinsu = what_insu.getSelectedItem().toString();
                final String savingbank = saving_bank.getSelectedItem().toString();
                final String pension_ = pension.getSelectedItem().toString();
                String s = "";
                if(ration_card.isChecked())
                {
                    s = s + "Ration Card\n";
                }
                if(aadhaar_card.isChecked())
                {
                    s = s + "Aadhar Card\n";
                }
                if(vao.isChecked())
                {
                    s = s + "VAO\n";
                }
                if(voter_id.isChecked())
                {
                    s = s + "Voter ID\n";
                }
                if(pan.isChecked())
                {
                    s = s + "PAN\n";
                }
                if(other.isChecked())
                {
                    s = s + "Other\n";
                }


                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = current_user.getUid();

                db_ds = FirebaseDatabase.getInstance().getReference().child("Digital Sakhi").child(uid);
                Map<String ,Object> usermap = new HashMap<>();
                usermap.put("38:Do you have a bank account?",bankaccount);
                usermap.put("39:What kind of bank balance account do you have?",accounttype);
                usermap.put("40:How frequently do you visit the bank?",bankvisit);
                usermap.put("41:If you don't go to the bank regurlarly, what are the reasons for the same?",reasonvisit);
                usermap.put("42:If not, why have you not been able to open account with a bank or finanacial institutions?",openaccount);
                usermap.put("43:What are the various identification documents that you have with you?",s);
                usermap.put("44:Has your household availed financial product from the bank?",financialpr);
                usermap.put("45:If you have, what is the kind of financial products that your household has availed?",financialpr1);
                usermap.put("46:Which of the following services have you availed from a bank?",servicebank);
                usermap.put("47:If your household has borrowed a loan from a bank, what is the type of loan that you have borrowed?",servicebank1);
                usermap.put("48:Have you taken out a loan before?",loantaken);
                usermap.put("49:From whom have you taken out a loan?",loanwhom);
                usermap.put("50:Where do you keep your money?",keepmoney);
                usermap.put("51:Do you have any debt? If so, how much",debttext);
                usermap.put("52:Do you feel you have access to formal credit linkages?",creditlinkage);
                usermap.put("53:Have you taken an insurance product from the bank?",insupro);
                usermap.put("54:If yes, what kind of insurance product have you availed?",whatinsu);
                usermap.put("55:Have you made savings in the bank or financial institutions?",savingbank);
                usermap.put("56:What kind of pension scheme has your household invested in?",pension_);
                db_ds.updateChildren(usermap);

                Toast.makeText(EN_ds_loan.this,"Saved",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(EN_ds_loan.this,EN_ds_business.class);
                startActivity(i);
            }
        });
    }
}
