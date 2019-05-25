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

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class EN_migration extends AppCompatActivity {

    EditText male_migrate,female_migrate,time_migrate;
    Spinner yes_no,reason_migrate;
    Toolbar toolbar;
    Button next;
    TextView enroll_no;
    enroll en;

    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_migration);

        toolbar = (Toolbar)findViewById(R.id.toolbar_emigration);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Migration");

        male_migrate = (EditText)findViewById(R.id.male_migrate_et);
        female_migrate = (EditText)findViewById(R.id.female_migrate_et);
        time_migrate = (EditText)findViewById(R.id.time_migrate_et);
        reason_migrate = (Spinner)findViewById(R.id.reason_migrate_spinner);
        yes_no = (Spinner)findViewById(R.id.family_migrate_spinner);
        next = (Button)findViewById(R.id.next7_btn);
        enroll_no = (TextView)findViewById(R.id.enrollment_id_number5);
        enroll_no.setText(en.getEnroll_id());

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String malemigrate = male_migrate.getText().toString();
                final String femalemigrate = female_migrate.getText().toString();
                final String timemigrate = time_migrate.getText().toString();
                final String reasonmigrate = reason_migrate.getSelectedItem().toString();
                final String yesno = yes_no.getSelectedItem().toString();

                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = current_user.getUid();

                db = FirebaseDatabase.getInstance().getReference().child("Livelihood Programme").child(uid);
                Map<String ,Object> usermap = new HashMap<>();
                usermap.put("065:Did any members of your family migrate last year for employment",yesno);
                usermap.put("066:Total no of Male Migrant",malemigrate);
                usermap.put("067:Total no of Female Migrant",femalemigrate);
                usermap.put("068:Total Time of Migration",timemigrate);
                usermap.put("069:Reason of Migrantion",reasonmigrate);
                db.updateChildren(usermap);

                Toast.makeText(EN_migration.this,"Saved",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(EN_migration.this,EN_food_security.class);
                startActivity(i);
            }
        });
    }
}
