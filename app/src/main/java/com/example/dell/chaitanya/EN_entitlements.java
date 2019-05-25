package com.example.dell.chaitanya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class EN_entitlements extends AppCompatActivity {

    Button next2_btn;
    Spinner ration_card,aadhaar_card,mgnrega,gas_conn,saving_bank_accn,joint_entitlement_house,joint_entitlement_land;
    Toolbar toolbar;
    TextView enroll_no;
    enroll en;

    private DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_entitlements);

        toolbar = (Toolbar)findViewById(R.id.toolbar_een);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Entitlements");

        next2_btn = (Button)findViewById(R.id.next2_btn);
        ration_card = (Spinner)findViewById(R.id.ration_card_spinner);
        aadhaar_card = (Spinner)findViewById(R.id.aadhaar_card_spinner);
        mgnrega = (Spinner)findViewById(R.id.mgnrega_spinner);
        gas_conn = (Spinner)findViewById(R.id.gas_conn_spinner);
        saving_bank_accn = (Spinner)findViewById(R.id.saving_bank_accn_spinner);
        joint_entitlement_house = (Spinner)findViewById(R.id.joint_entitlement_house_spinner);
        joint_entitlement_land = (Spinner)findViewById(R.id.joint_entitlement_land_spinner);

        enroll_no = (TextView)findViewById(R.id.enrollment_id_number);
        enroll_no.setText(en.getEnroll_id());

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
                usermap.put("024:Ration Card",rationcard);
                usermap.put("025:Aadhaar Card",aadhaarcard);
                usermap.put("026:MGNREGA",mgnrega_string);
                usermap.put("027:Gas Connection",gasconn);
                usermap.put("028:Saving bank account",saving_bankaccn);
                usermap.put("029:Joint entitlement of property husband and wife (House)",joint_entitlementhouse);
                usermap.put("030:Joint entitlement of property husband and wife (Land)",joint_entitlementland);
                db.updateChildren(usermap);

                Toast.makeText(EN_entitlements.this,"Saved",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(EN_entitlements.this,EN_occupation.class);
                startActivity(i);
            }
        });
    }
}
