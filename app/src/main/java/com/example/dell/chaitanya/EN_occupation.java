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

public class EN_occupation extends AppCompatActivity {

    Button next3_btn;
    Spinner adult_male,adult_female,children,livelihood_activities,if_no,livelihood_training,livelihood_support;
    EditText if_yes;
    TextView enroll_no;
    enroll en;
    Toolbar toolbar;

    private DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_occupation);

        toolbar = (Toolbar)findViewById(R.id.toolbar_eoccu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Occupation");

        adult_male = (Spinner)findViewById(R.id.adult_male_spinner);
        adult_female =(Spinner)findViewById(R.id.adult_female_spinner);
        children =(Spinner)findViewById(R.id.children_spinner);
        livelihood_activities =(Spinner)findViewById(R.id.livelihood_activities_spinner);
        if_no =(Spinner)findViewById(R.id.if_no_spinner);
        livelihood_training =(Spinner)findViewById(R.id.livelihood_training_spinner);
        livelihood_support =(Spinner)findViewById(R.id.livelihood_support_spinner);
        if_yes =(EditText) findViewById(R.id.if_yes_et);
        next3_btn = (Button)findViewById(R.id.next3_btn);

        enroll_no = (TextView)findViewById(R.id.enrollment_id_number1);
        enroll_no.setText(en.getEnroll_id());

        next3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String adultmale = adult_male.getSelectedItem().toString();
                final String adultfemale = adult_female.getSelectedItem().toString();
                final String children_string = children.getSelectedItem().toString();
                final String livelihoodactivities = livelihood_activities.getSelectedItem().toString();
                final String ifno = if_no.getSelectedItem().toString();
                final String livelihoodtraining = livelihood_training.getSelectedItem().toString();
                final String livelihoodsupport = livelihood_support.getSelectedItem().toString();
                final String ifyes = if_yes.getText().toString();


                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                assert current_user != null;
                String uid = current_user.getUid();
                db = FirebaseDatabase.getInstance().getReference().child("Livelihood Programme").child(uid);

                Map<String ,Object> usermap = new HashMap<>();
                usermap.put("031:Adult Male",adultmale);
                usermap.put("032:Adult Female",adultfemale);
                usermap.put("033:Children",children_string);
                usermap.put("034:Livelyhood activities through bank linkage",livelihoodactivities);
                usermap.put("035:If NO(followed by above question)",ifno);
                usermap.put("036:If YES(followed by above question)",ifyes);
                usermap.put("037:Reveived training for livelihood promotion through Govt Scheme",livelihoodtraining);
                usermap.put("038:Do you believe the women centric livelihood activities are supportive for aditional income generation",livelihoodsupport);
                db.updateChildren(usermap);

                Toast.makeText(EN_occupation.this,"Saved",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(EN_occupation.this,LivestockActivity.class);
                startActivity(i);
            }
        });
    }
}
