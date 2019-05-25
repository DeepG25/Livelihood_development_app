package com.example.dell.chaitanya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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

public class HI_personalinfo extends AppCompatActivity {

    private DatabaseReference db,enrollment_database;
    EditText headname,womenname,total_male,total_female,total_boy,total_girl,total_land,cultivated_land,irrigated_land;
    Spinner marital_status,member_text,social_category,economic_category,house_type,house_ele,source_income,members_inst,member_shg,farmer_category,irrigation_source;
    TextView enroll_no;
    Button next1;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hi_personalinfo);

        toolbar = (Toolbar)findViewById(R.id.toolbar_hpi);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("व्यक्तिगत जानकारी");

        headname = (EditText)findViewById(R.id.familyHead1);
        womenname = (EditText)findViewById(R.id.womenName1);
        marital_status = (Spinner)findViewById(R.id.marital_status_spinner1);
        member_text = (Spinner)findViewById(R.id.member_text_spinner1);
        social_category = (Spinner)findViewById(R.id.social_category_spinner1);
        economic_category = (Spinner)findViewById(R.id.economic_category_spinner1);
        house_type = (Spinner)findViewById(R.id.house_type_spinner1);
        house_ele = (Spinner)findViewById(R.id.ele_spinner1);
        total_male = (EditText)findViewById(R.id.total_male_et1);
        total_female = (EditText)findViewById(R.id.total_female_et1);
        total_boy = (EditText)findViewById(R.id.total_boy_et1);
        total_girl = (EditText)findViewById(R.id.total_girl_et1);
        source_income = (Spinner)findViewById(R.id.source_income_spinner1);
        members_inst = (Spinner)findViewById(R.id.members_inst_spinner1);
        member_shg = (Spinner)findViewById(R.id.member_shg_spinner1);
        farmer_category = (Spinner)findViewById(R.id.farmer_category_spinner1);
        total_land = (EditText)findViewById(R.id.total_land_et1);
        cultivated_land = (EditText)findViewById(R.id.cultivated_land_et1);
        irrigated_land = (EditText)findViewById(R.id.irrigated_land_et1);
        irrigation_source = (Spinner)findViewById(R.id.irrigation_source_spinner1);

        enroll_no = (TextView)findViewById(R.id.enroll_id_no1);
        next1 = (Button)findViewById(R.id.next1_btn1);

        //enroll_no.setText(enrollment_id.toString());

        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Head = headname.getText().toString();
                final String WomenName = womenname.getText().toString();
                final String maritalstatus = marital_status.getSelectedItem().toString();
                final String membertext = member_text.getSelectedItem().toString();
                final String socialcategory = social_category.getSelectedItem().toString();
                final String economiccategory = economic_category.getSelectedItem().toString();
                final String housetype = house_type.getSelectedItem().toString();
                final String houseele = house_ele.getSelectedItem().toString();
                final String totalmale = total_male.getText().toString();
                final String totalfemale = total_female.getText().toString();
                final String totalboy = total_boy.getText().toString();
                final String totalgirl = total_girl.getText().toString();
                final String sourceincome = source_income.getSelectedItem().toString();
                final String membersinst = members_inst.getSelectedItem().toString();
                final String membershg = member_shg.getSelectedItem().toString();
                final String farmercategory = farmer_category.getSelectedItem().toString();
                final String totalland = total_land.getText().toString();
                final String cultivatedland = cultivated_land.getText().toString();
                final String irrigatedland = irrigated_land.getText().toString();
                final String irrigationsource = irrigation_source.getSelectedItem().toString();

                String District = getIntent().getStringExtra("district");

                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                assert current_user != null;
                String uid = current_user.getUid();
                db = FirebaseDatabase.getInstance().getReference().child("Womens").child(uid);

                Map<String ,Object> usermap = new HashMap<>();
                usermap.put("01:District",District);
                usermap.put("04:Name of Head",Head);
                usermap.put("05:Name of Women",WomenName);
                usermap.put("06:Marital Status",maritalstatus);
                usermap.put("07:Are you member of any product group?",membertext);
                usermap.put("08:Social Category",socialcategory);
                usermap.put("09:Economic Category",economiccategory);
                usermap.put("10:Type of House",housetype);
                usermap.put("11:Does the house has authorized electricity?",houseele);
                usermap.put("12:Total number of Males",totalmale);
                usermap.put("13:Total number of Females",totalfemale);
                usermap.put("14:Total number of Boys",totalboy);
                usermap.put("15:Total number of Girls",totalgirl);
                usermap.put("16:Source Income",sourceincome);
                usermap.put("17:Membership in CBO or Institution",membersinst);
                usermap.put("18:Is the women member of SHG?",membershg);
                usermap.put("19:Farmer Category",farmercategory);
                usermap.put("20:Total Land",totalland);
                usermap.put("21:Cultivated Land",cultivatedland);
                usermap.put("22:Irrigated Land",irrigatedland);
                usermap.put("23:Irrigation Source",irrigationsource);
                db.setValue(usermap);

                Toast.makeText(HI_personalinfo.this,"Saved",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(HI_personalinfo.this,HI_entitlements.class);
                startActivity(i);
            }
        });
    }
}
