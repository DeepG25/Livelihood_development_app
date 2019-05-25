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

public class EN_personalinfo extends AppCompatActivity {

    private DatabaseReference db,enrollment_database;
    EditText headname,womenname,total_male,total_female,total_boy,total_girl,total_land,cultivated_land,irrigated_land;
    Spinner marital_status,member_text,social_category,economic_category,house_type,house_ele,source_income,members_inst,member_shg,farmer_category,irrigation_source;
    TextView enroll_no;
    Button next1;
    Toolbar toolbar;
    enroll en;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_personalinfo);

        toolbar = (Toolbar)findViewById(R.id.toolbar_epi);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Personal Information");

        headname = (EditText)findViewById(R.id.familyHead);
        womenname = (EditText)findViewById(R.id.womenName);
        marital_status = (Spinner)findViewById(R.id.marital_status_spinner);
        member_text = (Spinner)findViewById(R.id.member_text_spinner);
        social_category = (Spinner)findViewById(R.id.social_category_spinner);
        economic_category = (Spinner)findViewById(R.id.economic_category_spinner);
        house_type = (Spinner)findViewById(R.id.house_type_spinner);
        house_ele = (Spinner)findViewById(R.id.ele_spinner);
        total_male = (EditText)findViewById(R.id.total_male_et);
        total_female = (EditText)findViewById(R.id.total_female_et);
        total_boy = (EditText)findViewById(R.id.total_boy_et);
        total_girl = (EditText)findViewById(R.id.total_girl_et);
        source_income = (Spinner)findViewById(R.id.source_income_spinner);
        members_inst = (Spinner)findViewById(R.id.members_inst_spinner);
        member_shg = (Spinner)findViewById(R.id.member_shg_spinner);
        farmer_category = (Spinner)findViewById(R.id.farmer_category_spinner);
        total_land = (EditText)findViewById(R.id.total_land_et);
        cultivated_land = (EditText)findViewById(R.id.cultivated_land_et);
        irrigated_land = (EditText)findViewById(R.id.irrigated_land_et);
        irrigation_source = (Spinner)findViewById(R.id.irrigation_source_spinner);

        enroll_no = (TextView)findViewById(R.id.enroll_id_no);
        next1 = (Button)findViewById(R.id.next1_btn);

        enroll_no.setText(en.getEnroll_id());

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
                db = FirebaseDatabase.getInstance().getReference().child("Livelihood Programme").child(uid);

                Map<String ,Object> usermap = new HashMap<>();
                usermap.put("000:Enrollment ID",enroll_no.getText().toString());
                usermap.put("004:Name of Head",Head);
                usermap.put("005:Name of Women",WomenName);
                usermap.put("006:Marital Status",maritalstatus);
                usermap.put("007:Are you member of any product group?",membertext);
                usermap.put("008:Social Category",socialcategory);
                usermap.put("009:Economic Category",economiccategory);
                usermap.put("010:Type of House",housetype);
                usermap.put("011:Does the house has authorized electricity?",houseele);
                usermap.put("012:Total number of Males",totalmale);
                usermap.put("013:Total number of Females",totalfemale);
                usermap.put("014:Total number of Boys",totalboy);
                usermap.put("015:Total number of Girls",totalgirl);
                usermap.put("016:Source Income",sourceincome);
                usermap.put("017:Membership in CBO or Institution",membersinst);
                usermap.put("018:Is the women member of SHG?",membershg);
                usermap.put("019:Farmer Category",farmercategory);
                usermap.put("020:Total Land",totalland);
                usermap.put("021:Cultivated Land",cultivatedland);
                usermap.put("022:Irrigated Land",irrigatedland);
                usermap.put("023:Irrigation Source",irrigationsource);
                db.updateChildren(usermap);

                Toast.makeText(EN_personalinfo.this,"Saved",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(EN_personalinfo.this,EN_entitlements.class);
                startActivity(i);
                //enrollment_id  = enrollment_id + 1;

                //enrollment_database = FirebaseDatabase.getInstance().getReference().child("value");
                //enrollment_database.setValue(enrollment_id);

                //enroll_no.setText(enrollment_id.toString());
            }
        });
    }
}
