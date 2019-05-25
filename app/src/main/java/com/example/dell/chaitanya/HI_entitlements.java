package com.example.dell.chaitanya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class HI_entitlements extends AppCompatActivity {

    Button next2_btn;
    Spinner ration_card,aadhaar_card,mgnrega,gas_conn,saving_bank_accn,joint_entitlement_house,joint_entitlement_land;
    Toolbar toolbar;

    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hi_entitlements);

        toolbar = (Toolbar)findViewById(R.id.toolbar_hen);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("पात्रता");

        next2_btn = (Button)findViewById(R.id.next2_btn1);
        ration_card = (Spinner)findViewById(R.id.ration_card_spinner1);
        aadhaar_card = (Spinner)findViewById(R.id.aadhaar_card_spinner1);
        mgnrega = (Spinner)findViewById(R.id.mgnrega_spinner1);
        gas_conn = (Spinner)findViewById(R.id.gas_conn_spinner1);
        saving_bank_accn = (Spinner)findViewById(R.id.saving_bank_accn_spinner1);
        joint_entitlement_house = (Spinner)findViewById(R.id.joint_entitlement_house_spinner1);
        joint_entitlement_land = (Spinner)findViewById(R.id.joint_entitlement_land_spinner1);

        next2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String rationcard = ration_card.getSelectedItem().toString();
                final String aadhaarcard = aadhaar_card.getSelectedItem().toString();
                final String mgnrega_string = mgnrega.getSelectedItem().toString();
                final String gasconn = gas_conn.getSelectedItem().toString();
                final String saving_bankaccn = saving_bank_accn.getSelectedItem().toString();
                final String joint_entitlementhouse = joint_entitlement_house.getSelectedItem().toString();
                final String joint_entitlementland = joint_entitlement_land.getSelectedItem().toString();


                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                assert current_user != null;
                String uid = current_user.getUid();
                db = FirebaseDatabase.getInstance().getReference().child("Livelihood Programme").child(uid);

                Map<String ,Object> usermap = new HashMap<>();
                usermap.put("24:Ration Card",rationcard);
                usermap.put("25:Aadhaar Card",aadhaarcard);
                usermap.put("26:MGNREGA",mgnrega_string);
                usermap.put("27:Gas Connection",gasconn);
                usermap.put("28:Saving bank account",saving_bankaccn);
                usermap.put("29:Joint entitlement of property husband and wife (House)",joint_entitlementhouse);
                usermap.put("30:Joint entitlement of property husband and wife (Land)",joint_entitlementland);
                db.updateChildren(usermap);

                Toast.makeText(HI_entitlements.this,"Saved",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(HI_entitlements.this,HI_occupation.class);
                startActivity(i);
            }
        });
    }
}
