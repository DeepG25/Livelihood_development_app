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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EN_ds_household_finance extends AppCompatActivity {

    CheckBox salary,business,investment,labour,other,banking_card,mobile_wallet,upi,aeps,ussd,none;
    EditText income_prior,income_individual,save_money;
    Spinner budget,cost,save_bank,investments,digital;
    Button next;
    Toolbar toolbar;
    private DatabaseReference db_ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_ds_household_finance);

        toolbar = (Toolbar)findViewById(R.id.toolbar_ds_household);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Household Finances");

        salary = (CheckBox)findViewById(R.id.salary_box);
        business = (CheckBox)findViewById(R.id.business_box);
        investment = (CheckBox)findViewById(R.id.investments_box);
        labour = (CheckBox)findViewById(R.id.labour_box);
        other = (CheckBox)findViewById(R.id.other_box);
        banking_card = (CheckBox)findViewById(R.id.banking_card_box);
        mobile_wallet = (CheckBox)findViewById(R.id.mobile_wallet_box);
        upi = (CheckBox)findViewById(R.id.upi_box);
        aeps = (CheckBox)findViewById(R.id.aeps_box);
        ussd = (CheckBox)findViewById(R.id.ussd_box);
        none = (CheckBox)findViewById(R.id.none_box);
        income_prior = (EditText)findViewById(R.id.income_prior_et_ds);
        income_individual = (EditText)findViewById(R.id.income_individual_et_ds);
        save_money = (EditText)findViewById(R.id.save_money_et_ds);
        budget = (Spinner)findViewById(R.id.budget_spinner_ds);
        cost = (Spinner)findViewById(R.id.cost_spinner_ds);
        save_bank = (Spinner)findViewById(R.id.save_bank_spinner_ds);
        investments = (Spinner)findViewById(R.id.investments_spinner_ds);
        digital = (Spinner)findViewById(R.id.digital_spinner_ds);
        next = (Button)findViewById(R.id.next4_btn_ds);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = "";
                if(salary.isChecked())
                {
                    s = s + "Salary ";
                }
                if(business.isChecked())
                {
                    s = s + "Business ";
                }
                if(investment.isChecked())
                {
                    s = s + "Investment ";
                }
                if(labour.isChecked())
                {
                    s = s + "Labour ";
                }
                if(other.isChecked())
                {
                    s = s + "Other ";
                }

                String s1 = "";
                if(banking_card.isChecked())
                {
                    s1 = s1 + "Banking Card ";
                }
                if(mobile_wallet.isChecked())
                {
                    s1 = s1 + "Mobile Wallet ";
                }
                if(upi.isChecked())
                {
                    s1 = s1 + "UPI ";
                }
                if(aeps.isChecked())
                {
                    s1 = s1 + "AePS ";
                }
                if(ussd.isChecked())
                {
                    s1 = s1 + "USSD ";
                }
                if(none.isChecked())
                {
                    s1 = s1 + "None ";
                }

                final String incomeprior = income_prior.getText().toString();
                final String incomeindividual = income_individual.getText().toString();
                final String savemoney = save_money.getText().toString();
                final String budget_ = budget.getSelectedItem().toString();
                final String cost_ = cost.getSelectedItem().toString();
                final String savebank = save_bank.getSelectedItem().toString();
                final String investments_ = investments.getSelectedItem().toString();
                final String digital_ = digital.getSelectedItem().toString();

                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = current_user.getUid();

                db_ds = FirebaseDatabase.getInstance().getReference().child("Digital Sakhi").child(uid);
                Map<String ,Object> usermap = new HashMap<>();
                usermap.put("28:What are all of your sources of income?",s);
                usermap.put("29:What was your household income per month prior to this program? (Beginning of the Program)",incomeprior);
                usermap.put("30:What is your individual income?",incomeindividual);
                usermap.put("31:Do you have a monthly budget for your Household?",budget_);
                usermap.put("32:Does your income cover all living costs",cost_);
                usermap.put("33:How much do you save each month?",savemoney);
                usermap.put("34:Do you save money in the Bank?",savebank);
                usermap.put("35:Do you have Investments?",investments_);
                usermap.put("36:Have you ever used Digital modes of payments?",digital_);
                usermap.put("37:What digital modes of payments have you used?",s1);
                db_ds.updateChildren(usermap);

                Toast.makeText(EN_ds_household_finance.this,"Saved",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(EN_ds_household_finance.this,EN_ds_loan.class);
                startActivity(i);
            }
        });

    }
}
