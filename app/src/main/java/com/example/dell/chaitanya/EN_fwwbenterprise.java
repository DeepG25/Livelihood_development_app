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

public class EN_fwwbenterprise extends AppCompatActivity {

    EditText buisness_activity_et_fwwb_java,no_years_et_fwwb_java,devoted_years_et_fwwb_java,fixed_capital_et_fwwb_java,working_capital_et_fwwb_java
            ,total_investment_et_fwwb_java,working_capital_amount_et_fwwb_java,fixed_capital_amount_et_fwwb_java,if_yes_persons_et_fwwb_java
            ,wages_per_persons_et_fwwb_java,current_sale_et_fwwb_java,current_profit_et_fwwb_java,expense_breakup_et_fwwb_java;
    Spinner entrepreneur_type_spinner_fwwb_java,seasonal_activity_spinner_fwwb_java,nature_business_spinner_fwwb_java,machinery_business_spinner_fwwb_java
            ,start_business_spinner_fwwb_java,premises_type_spinner_fwwb_java,major_source_spinner_fwwb_java,working_capital_details_spinner_fwwb_java
            ,fixed_capital_details_spinner_fwwb_java,manage_buisness_spinner_fwwb_java,employed_personss_spinner_fwwb_java,financial_record_spinner_fwwb_java
            ,product_service_spinner_fwwb_java;
    Button next_btn_3;
    TextView eid_enterprise;
    Toolbar toolbar;
    enroll en;
    CheckBox check_perandfam,check_casandcre,check_recandpay,check_stoandinv,check_busexp,check_wom,check_snib,check_adv,check_exh,check_brand,check_price,check_pack;
    private DatabaseReference db_fwwb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_fwwbenterprise);

        toolbar = (Toolbar)findViewById(R.id.toolbar_fwwb_enterprise);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Enterprise Details");

        buisness_activity_et_fwwb_java = (EditText)findViewById(R.id.buisness_activity_et_fwwb);
        no_years_et_fwwb_java = (EditText)findViewById(R.id.no_years_et_fwwb);
        devoted_years_et_fwwb_java = (EditText)findViewById(R.id.devoted_years_et_fwwb);
        fixed_capital_et_fwwb_java = (EditText)findViewById(R.id.fixed_capital_et_fwwb);
        working_capital_et_fwwb_java = (EditText)findViewById(R.id.working_capital_et_fwwb);
        total_investment_et_fwwb_java = (EditText)findViewById(R.id.total_investment_et_fwwb);
        working_capital_amount_et_fwwb_java = (EditText)findViewById(R.id.working_capital_amount_et_fwwb);
        fixed_capital_amount_et_fwwb_java = (EditText)findViewById(R.id.fixed_capital_amount_et_fwwb);
        if_yes_persons_et_fwwb_java = (EditText)findViewById(R.id.if_yes_persons_et_fwwb);
        wages_per_persons_et_fwwb_java = (EditText)findViewById(R.id.wages_per_persons_et_fwwb);
        current_sale_et_fwwb_java = (EditText)findViewById(R.id.current_sale_et_fwwb);
        current_profit_et_fwwb_java = (EditText)findViewById(R.id.current_profit_et_fwwb);
        expense_breakup_et_fwwb_java = (EditText)findViewById(R.id.expense_breakup_et_fwwb);

        entrepreneur_type_spinner_fwwb_java = (Spinner)findViewById(R.id.entrepreneur_type_spinner_fwwb);
        seasonal_activity_spinner_fwwb_java = (Spinner)findViewById(R.id.seasonal_activity_spinner_fwwb);
        nature_business_spinner_fwwb_java = (Spinner)findViewById(R.id.nature_business_spinner_fwwb);
        machinery_business_spinner_fwwb_java = (Spinner)findViewById(R.id.machinery_business_spinner_fwwb);
        start_business_spinner_fwwb_java = (Spinner)findViewById(R.id.start_business_spinner_fwwb);
        premises_type_spinner_fwwb_java = (Spinner)findViewById(R.id.premises_type_spinner_fwwb);
        major_source_spinner_fwwb_java = (Spinner)findViewById(R.id.major_source_spinner_fwwb);
        working_capital_details_spinner_fwwb_java = (Spinner)findViewById(R.id.working_capital_details_spinner_fwwb);
        fixed_capital_details_spinner_fwwb_java = (Spinner)findViewById(R.id.fixed_capital_details_spinner_fwwb);
        manage_buisness_spinner_fwwb_java = (Spinner)findViewById(R.id.manage_buisness_spinner_fwwb);
        employed_personss_spinner_fwwb_java = (Spinner)findViewById(R.id.employed_personss_spinner_fwwb);
        financial_record_spinner_fwwb_java = (Spinner)findViewById(R.id.financial_record_spinner_fwwb);
        product_service_spinner_fwwb_java = (Spinner)findViewById(R.id.product_service_spinner_fwwb);

        next_btn_3 = (Button)findViewById(R.id.next3_btn_fwwb);
        eid_enterprise = (TextView)findViewById(R.id.enrollment_id_number_fwwb_4);

        check_perandfam = (CheckBox)findViewById(R.id.if_yes_financial_record_checkbox_fwwb_1);
        check_casandcre = (CheckBox)findViewById(R.id.if_yes_financial_record_checkbox_fwwb_2);
        check_recandpay = (CheckBox)findViewById(R.id.if_yes_financial_record_checkbox_fwwb_3);
        check_stoandinv = (CheckBox)findViewById(R.id.if_yes_financial_record_checkbox_fwwb_4);
        check_busexp = (CheckBox)findViewById(R.id.if_yes_financial_record_checkbox_fwwb_5);
        check_wom = (CheckBox)findViewById(R.id.if_yes_promote_checkbox_fwwb_1);
        check_snib = (CheckBox)findViewById(R.id.if_yes_promote_checkbox_fwwb_2);
        check_adv = (CheckBox)findViewById(R.id.if_yes_promote_checkbox_fwwb_3);
        check_exh = (CheckBox)findViewById(R.id.if_yes_promote_checkbox_fwwb_4);
        check_brand = (CheckBox)findViewById(R.id.are_you_doing_checkbox_fwwb_1);
        check_price = (CheckBox)findViewById(R.id.are_you_doing_checkbox_fwwb_2);
        check_pack = (CheckBox)findViewById(R.id.are_you_doing_checkbox_fwwb_3);

        eid_enterprise.setText(en.getEnroll_id());

        next_btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String buisness_activity_et_fwwb_string = buisness_activity_et_fwwb_java.getText().toString();
                String no_years_et_fwwb_string = no_years_et_fwwb_java.getText().toString();
                String devoted_years_et_fwwb_string = devoted_years_et_fwwb_java.getText().toString();
                String fixed_capital_et_fwwb_string = fixed_capital_et_fwwb_java.getText().toString();
                String working_capital_et_fwwb_string = working_capital_et_fwwb_java.getText().toString();
                String total_investment_et_fwwb_string = total_investment_et_fwwb_java.getText().toString();
                String working_capital_amount_et_fwwb_string = working_capital_amount_et_fwwb_java.getText().toString();
                String fixed_capital_amount_et_fwwb_string = fixed_capital_amount_et_fwwb_java.getText().toString();
                String if_yes_persons_et_fwwb_string = if_yes_persons_et_fwwb_java.getText().toString();
                String wages_per_persons_et_fwwb_string = wages_per_persons_et_fwwb_java.getText().toString();
                String current_sale_et_fwwb_string = current_sale_et_fwwb_java.getText().toString();
                String current_profit_et_fwwb_string = current_profit_et_fwwb_java.getText().toString();
                String expense_breakup_et_fwwb_string = expense_breakup_et_fwwb_java.getText().toString();

                String entrepreneur_type_spinner_fwwb_string = entrepreneur_type_spinner_fwwb_java.getSelectedItem().toString();
                String seasonal_activity_spinner_fwwb_string = seasonal_activity_spinner_fwwb_java.getSelectedItem().toString();
                String nature_business_spinner_fwwb_string = nature_business_spinner_fwwb_java.getSelectedItem().toString();
                String machinery_business_spinner_fwwb_string = machinery_business_spinner_fwwb_java.getSelectedItem().toString();
                String start_business_spinner_fwwb_string = start_business_spinner_fwwb_java.getSelectedItem().toString();
                String premises_type_spinner_fwwb_string = premises_type_spinner_fwwb_java.getSelectedItem().toString();
                String major_source_spinner_fwwb_string = major_source_spinner_fwwb_java.getSelectedItem().toString();
                String working_capital_details_spinner_fwwb_string = working_capital_details_spinner_fwwb_java.getSelectedItem().toString();
                String fixed_capital_details_spinner_fwwb_string = fixed_capital_details_spinner_fwwb_java.getSelectedItem().toString();
                String manage_buisness_spinner_fwwb_string = manage_buisness_spinner_fwwb_java.getSelectedItem().toString();
                String employed_personss_spinner_fwwb_string = employed_personss_spinner_fwwb_java.getSelectedItem().toString();
                String financial_record_spinner_fwwb_string = financial_record_spinner_fwwb_java.getSelectedItem().toString();
                String product_service_spinner_fwwb_string = product_service_spinner_fwwb_java.getSelectedItem().toString();

                String Multiple_4_1 = "";
                if (check_perandfam.isChecked())
                {
                    Multiple_4_1 += "Personal and family exp. ";
                }
                if (check_casandcre.isChecked())
                {
                    Multiple_4_1 += "Cash and credit sales ";
                }
                if (check_recandpay.isChecked())
                {
                    Multiple_4_1 += "Receivable and payable ";
                }
                if (check_stoandinv.isChecked())
                {
                    Multiple_4_1 += "Stock and inventory ";
                }
                if (check_busexp.isChecked())
                {
                    Multiple_4_1 += "Business Expenditure";
                }

                String Multiple_4_2 = "";
                if(check_wom.isChecked())
                {
                    Multiple_4_2 += "Word of Mouth ";
                }
                if(check_snib.isChecked())
                {
                    Multiple_4_2 += "Shop Name in board ";
                }
                if(check_adv.isChecked())
                {
                    Multiple_4_2 += "Advertisement ";
                }
                if(check_exh.isChecked())
                {
                    Multiple_4_2 += "Exhibition";
                }

                String Multiple_4_3 = "";
                if(check_brand.isChecked())
                {
                    Multiple_4_3 += "Branding ";
                }
                if(check_price.isChecked())
                {
                    Multiple_4_3 += "Price Labelling ";
                }
                if(check_pack.isChecked())
                {
                    Multiple_4_3 += "Packaging";
                }

                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = current_user.getUid();

                db_fwwb = FirebaseDatabase.getInstance().getReference().child("FWWB Program").child(uid);
                Map<String, Object> usermap = new HashMap<>();
                usermap.put("45:Type of Entrepreneur",entrepreneur_type_spinner_fwwb_string);
                usermap.put("46:Business Activity",buisness_activity_et_fwwb_string);
                usermap.put("47:No of Years in Business",no_years_et_fwwb_string);
                usermap.put("48:Seasonal Activity",seasonal_activity_spinner_fwwb_string);
                usermap.put("49:How many hours you devote in your business (Hrs)",devoted_years_et_fwwb_string);
                usermap.put("50:Nature of business and Type",nature_business_spinner_fwwb_string);
                usermap.put("51:Do you own equipment or machinery in your business",machinery_business_spinner_fwwb_string);
                usermap.put("52:How the business was started",start_business_spinner_fwwb_string);
                usermap.put("53:Type of premises for the business",premises_type_spinner_fwwb_string);
                usermap.put("54:Fixed capital (INR)",fixed_capital_et_fwwb_string);
                usermap.put("55:Working Capital (INR)",working_capital_et_fwwb_string);
                usermap.put("56:Total investment",total_investment_et_fwwb_string);
                usermap.put("57:Major source",major_source_spinner_fwwb_string);
                usermap.put("58:Current working capital",working_capital_details_spinner_fwwb_string);
                usermap.put("59:Amount (INR)",working_capital_amount_et_fwwb_string);
                usermap.put("60:Current Fixed capital",fixed_capital_details_spinner_fwwb_string);
                usermap.put("61:Amount (INR)",fixed_capital_amount_et_fwwb_string);
                usermap.put("62:Who manages the Business",manage_buisness_spinner_fwwb_string);
                usermap.put("63:Have you employed persons",employed_personss_spinner_fwwb_string);
                usermap.put("64:If YES, how many person you have employed",if_yes_persons_et_fwwb_string);
                usermap.put("65:Wages per employee",wages_per_persons_et_fwwb_string);
                usermap.put("66:Current Sales",current_sale_et_fwwb_string);
                usermap.put("67:Current Profit",current_profit_et_fwwb_string);
                usermap.put("68:Break up of expenses (Activity - Amount(Rs))",expense_breakup_et_fwwb_string);
                usermap.put("69:Do you maintain financial records for business",financial_record_spinner_fwwb_string);
                usermap.put("70:If YES, which one",Multiple_4_1);         //CHECKBOX
                usermap.put("71:Do you promote your product and services",product_service_spinner_fwwb_string);
                usermap.put("72:If YES, how you promote",Multiple_4_2);   //CHECKBOX
                usermap.put("73:You are doing",Multiple_4_3);             //CHECKBOX
                db_fwwb.updateChildren(usermap);

                Toast.makeText(EN_fwwbenterprise.this," Final Saved",Toast.LENGTH_SHORT).show();

                FirebaseAuth.getInstance().signOut();

                Intent i = new Intent(EN_fwwbenterprise.this,Login.class);
                startActivity(i);
                finish();
            }
        });
    }
}
