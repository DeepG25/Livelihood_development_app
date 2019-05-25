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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EN_agriculture_inputs extends AppCompatActivity {

    Spinner purchase_process,participate,agricultural_advice;
    Button next;
    Toolbar toolbar;
    ProgressDialog progressDialog;
    TextView enroll_no;
    enroll en;

    private DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_agriculture_inputs);

        progressDialog = new ProgressDialog(EN_agriculture_inputs.this);

        toolbar = (Toolbar)findViewById(R.id.toolbar_eagriculture_inputs);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Agriculture Inputs and Information");

        purchase_process = (Spinner)findViewById(R.id.purchase_process_spinner);
        participate = (Spinner)findViewById(R.id.participate_spinner);
        agricultural_advice = (Spinner)findViewById(R.id.agricultural_advice_spinner);
        next = (Button)findViewById(R.id.next11_btn);

        enroll_no = (TextView)findViewById(R.id.enrollment_id_number9);
        enroll_no.setText(en.getEnroll_id());

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String purchaseprocess = purchase_process.getSelectedItem().toString();
                final String participate_ = participate.getSelectedItem().toString();
                final String agriculturaladvice = agricultural_advice.getSelectedItem().toString();

                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                assert current_user != null;
                String uid = current_user.getUid();
                db = FirebaseDatabase.getInstance().getReference().child("Livelihood Programme").child(uid);

                Map<String ,Object> usermap = new HashMap<>();
                usermap.put("117:Was there a purchase process to aggregate agriculture inputs in all Villages/Groups",purchaseprocess);
                usermap.put("118:If YES, did you participate in that",participate_);
                usermap.put("119:Did you regularly get agricultural advice from Agricultural Dept.",agriculturaladvice);
                db.updateChildren(usermap);

                Toast.makeText(EN_agriculture_inputs.this,"Saved",Toast.LENGTH_SHORT).show();

                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(EN_agriculture_inputs.this,Login.class);
                startActivity(i);
                finish();
            }
        });
    }
}
