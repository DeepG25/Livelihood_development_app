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

public class EN_fwwbbasic extends AppCompatActivity {

    EditText name,contact,age,state,district,taluka,village,start_date,males_no,females_no,children_no,adults_no,total_no,current_household_et;
    Spinner assement_text,graduated_text,marital_status_text,specific_skill,training,group_model,source_household_income_spinner_fwwb_java;
    Button next_btn;
    TextView eid_basic;
    Toolbar toolbar;
    CheckBox check_land,check_house,check_shop,check_vehicle,check_machine,check_livestock;
    private DatabaseReference db_fwwb;
    enroll en;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_fwwbbasic);
        toolbar = (Toolbar)findViewById(R.id.toolbar_fwwb_basic);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Basic Information");
        name = (EditText)findViewById(R.id.name_fwwb);
        contact = (EditText)findViewById(R.id.contact_no_fwwb);
        age = (EditText)findViewById(R.id.age_fwwb);
        state = (EditText)findViewById(R.id.state_fwwb);
        district = (EditText)findViewById(R.id.district_fwwb);
        taluka = (EditText)findViewById(R.id.taluka_fwwb);
        village = (EditText)findViewById(R.id.village_fwwb);
        specific_skill = (Spinner) findViewById(R.id.specific_skill_spinner_fwwb);
        training = (Spinner) findViewById(R.id.training_spinner_fwwb);
        start_date = (EditText)findViewById(R.id.start_date_fwwb);
        males_no = (EditText)findViewById(R.id.males_no_et_fwwb);
        females_no = (EditText)findViewById(R.id.females_no_et_fwwb);
        children_no = (EditText)findViewById(R.id.children_no_et_fwwb);
        adults_no = (EditText)findViewById(R.id.adults_no_et_fwwb);
        total_no = (EditText)findViewById(R.id.total_no_et_fwwb);
        group_model = (Spinner)findViewById(R.id.group_model_spinner_fwwb);
        assement_text = (Spinner)findViewById(R.id.assement_spinner_fwwb);
        graduated_text = (Spinner)findViewById(R.id.graduated_spinner_fwwb);
        marital_status_text = (Spinner)findViewById(R.id.marital_status_spinner_fwwb);
        current_household_et = (EditText)findViewById(R.id.current_income_et_fwwb);
        source_household_income_spinner_fwwb_java = (Spinner)findViewById(R.id.source_household_income_spinner_fwwb);
        next_btn = (Button)findViewById(R.id.next_btn_fwwb);
        eid_basic = (TextView)findViewById(R.id.enrollment_id_number_basic_fwwb_1);

        check_land = (CheckBox)findViewById(R.id.household_assets_checkbox_fwwb_1);
        check_house = (CheckBox)findViewById(R.id.household_assets_checkbox_fwwb_2);
        check_shop = (CheckBox)findViewById(R.id.household_assets_checkbox_fwwb_3);
        check_vehicle = (CheckBox)findViewById(R.id.household_assets_checkbox_fwwb_4);
        check_machine = (CheckBox)findViewById(R.id.household_assets_checkbox_fwwb_5);
        check_livestock = (CheckBox)findViewById(R.id.household_assets_checkbox_fwwb_6);


        eid_basic.setText(en.getEnroll_id());


        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_fwwb = name.getText().toString();
                String contact_fwwb = contact.getText().toString();
                String age_fwwb = age.getText().toString();
                String state_fwwb = state.getText().toString();
                String district_fwwb = district.getText().toString();
                String taluka_fwwb = taluka.getText().toString();
                String village_fwwb = village.getText().toString();
                String specific_skill_fwwb = specific_skill.getSelectedItem().toString();
                String training_fwwb = training.getSelectedItem().toString();
                String startdate = start_date.getText().toString();
                String malesno = males_no.getText().toString();
                String femalesno = females_no.getText().toString();
                String childrenno = children_no.getText().toString();
                String adultsno = adults_no.getText().toString();
                String totalno = total_no.getText().toString();
                String groupmodel = group_model.getSelectedItem().toString();
                String assement = assement_text.getSelectedItem().toString();
                String graduated = graduated_text.getSelectedItem().toString();
                String marital_status = marital_status_text.getSelectedItem().toString();
                String current_income_string = current_household_et.getText().toString();
                String source_household_income_spinner_fwwb_string = source_household_income_spinner_fwwb_java.getSelectedItem().toString();

                String Multiple_1 = "";
                if (check_land.isChecked())
                {
                    Multiple_1 += "Land ";
                }
                if (check_house.isChecked())
                {
                    Multiple_1 += "House ";
                }
                if (check_shop.isChecked())
                {
                    Multiple_1 += "Shop ";
                }
                if (check_vehicle.isChecked())
                {
                    Multiple_1 += "Vehicle ";
                }
                if (check_machine.isChecked())
                {
                    Multiple_1 += "Machine ";
                }
                if (check_livestock.isChecked())
                {
                    Multiple_1 += "Livestock";
                }

                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = current_user.getUid();

                db_fwwb = FirebaseDatabase.getInstance().getReference().child("FWWB Program").child(uid);
                HashMap<String ,String> usermap = new HashMap<>();
                usermap.put("00:Enrollment ID",eid_basic.getText().toString());
                usermap.put("01:Name",name_fwwb);
                usermap.put("02:Contact Number",contact_fwwb);
                usermap.put("03:Age",age_fwwb);
                usermap.put("04:State",state_fwwb);
                usermap.put("05:District",district_fwwb);
                usermap.put("06:Taluka",taluka_fwwb);
                usermap.put("07:Village",village_fwwb);
                usermap.put("08:Assessment Conducted",assement);
                usermap.put("09:Graduated to next phase",graduated);
                usermap.put("10:Type of specific livelihood technical skill traning required",specific_skill_fwwb);
                usermap.put("11:Undertaken Training",training_fwwb);
                usermap.put("12:Started Business on Year",startdate);
                usermap.put("13:Marital Status",marital_status);
                usermap.put("14:Males",malesno);
                usermap.put("15:Females",femalesno);
                usermap.put("16:Children",childrenno);
                usermap.put("17:Adults",adultsno);
                usermap.put("18:Total",totalno);
                usermap.put("19:Group Model",groupmodel);
                usermap.put("20:Source of Household Income",source_household_income_spinner_fwwb_string);
                usermap.put("21:Current Household Income (INR)",current_income_string);
                usermap.put("22:Household Assets",Multiple_1);                       //CHECKBOX
                db_fwwb.setValue(usermap);

                Toast.makeText(EN_fwwbbasic.this,"Saved",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(EN_fwwbbasic.this,EN_fwwbsaving.class);
                startActivity(i);
            }
        });


    }
}
