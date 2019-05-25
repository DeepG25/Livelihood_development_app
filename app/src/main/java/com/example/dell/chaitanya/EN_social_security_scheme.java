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

import java.util.HashMap;
import java.util.Map;

public class EN_social_security_scheme extends AppCompatActivity {

    Spinner pmuy,jsy,apy,pmpby,gmsavy,ssy,women_entitlement,pmjjby,pmsby,family_scheme,if_no_scheme;
    Button next;
    Toolbar toolbar;
    TextView enroll_no;
    enroll en;

    private DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_social_security_scheme);

        toolbar = (Toolbar)findViewById(R.id.toolbar_esocial_scheme);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Social Security Scheme");

        pmuy = (Spinner)findViewById(R.id.pmuy_spinner);
        jsy = (Spinner)findViewById(R.id.jsy_spinner);
        apy = (Spinner)findViewById(R.id.apy_spinner);
        pmpby = (Spinner)findViewById(R.id.pmpby_spinner);
        gmsavy = (Spinner)findViewById(R.id.gmsavy_spinner);
        ssy = (Spinner)findViewById(R.id.ssy_spinner);
        women_entitlement = (Spinner)findViewById(R.id.women_entitlement_spinner);
        pmjjby = (Spinner)findViewById(R.id.pmjjby_spinner);
        pmsby = (Spinner)findViewById(R.id.pmsby_spinner);
        family_scheme = (Spinner)findViewById(R.id.family_scheme_spinner);
        if_no_scheme = (Spinner)findViewById(R.id.if_no_scheme_spinner);
        next = (Button)findViewById(R.id.next9_btn);

        enroll_no = (TextView)findViewById(R.id.enrollment_id_number6);
        enroll_no.setText(en.getEnroll_id());

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String pmuy_ = pmuy.getSelectedItem().toString();
                final String jsy_ = jsy.getSelectedItem().toString();
                final String apy_ = apy.getSelectedItem().toString();
                final String pmpby_ = pmpby.getSelectedItem().toString();
                final String gmsavy_ = gmsavy.getSelectedItem().toString();
                final String ssy_ = ssy.getSelectedItem().toString();
                final String women_entitlement_ = women_entitlement.getSelectedItem().toString();
                final String pmjjby_ = pmjjby.getSelectedItem().toString();
                final String pmsby_ = pmsby.getSelectedItem().toString();
                final String family_scheme_ = family_scheme.getSelectedItem().toString();
                final String if_no_scheme_ = if_no_scheme.getSelectedItem().toString();

                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = current_user.getUid();

                db = FirebaseDatabase.getInstance().getReference().child("Livelihood Programme").child(uid);
                Map<String ,Object> usermap = new HashMap<>();
                usermap.put("079:Recevied Pradhanmanrti Ujjwal Yojna (PMUY)",pmuy_);
                usermap.put("080:Janani Suraksha Yojna (JSY)",jsy_);
                usermap.put("081:Atal Pension Yojna (PMUY)",apy_);
                usermap.put("082:Pradhanmantri Pik Bima Yojna (PMPBY)",pmpby_);
                usermap.put("083:Gopinath Munde Shetkari Aapghat Vima Yojna (GMSAVY)",gmsavy_);
                usermap.put("084:Suknya Samruddhi Yojna (SSY)",ssy_);
                usermap.put("085:Use of women entitlement act (for House and Land)",women_entitlement_);
                usermap.put("086:Pradhan Mantri Jeevan Jyoti Bima Yojna (PMJJBY))",pmjjby_);
                usermap.put("087:Pradhan Mantri Suraksha Bima Yojna (PMSBY)",pmsby_);
                usermap.put("088:Does your family applied for any schemes at respetive govt office",family_scheme_);
                usermap.put("089:If NO then why",if_no_scheme_);
                db.updateChildren(usermap);

                Toast.makeText(EN_social_security_scheme.this,"Saved",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(EN_social_security_scheme.this,EN_Livelihood_scheme.class);
                startActivity(i);
            }
        });
    }
}
