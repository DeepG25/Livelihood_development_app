package com.example.dell.chaitanya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EN_ds_brand_awarness extends AppCompatActivity {

    Spinner ltfs_familier,ltfs_firsthear,hear_count,join_anyprogram_start,join_anyprogram_end;
    EditText financial;
    Button next;
    Toolbar toolbar;

    private DatabaseReference db_ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_ds_brand_awarness);

        toolbar = (Toolbar)findViewById(R.id.toolbar_ds_awareness);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Brand Awareness");

        ltfs_familier = (Spinner)findViewById(R.id.ltfs_familier_spinner_ds);
        ltfs_firsthear = (Spinner)findViewById(R.id.ltfs_firsthear_spinner_ds);
        hear_count = (Spinner)findViewById(R.id.hear_count_spinner_ds);
        join_anyprogram_start = (Spinner)findViewById(R.id.join_anyprogram_start_spinner_ds);
        join_anyprogram_end = (Spinner)findViewById(R.id.join_anyprogram_end_spinner_ds);
        financial = (EditText)findViewById(R.id.financial_et_ds);
        next = (Button)findViewById(R.id.next1_btn_ds);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ltfsfamilier = ltfs_familier.getSelectedItem().toString();
                final String ltfsfirsthear = ltfs_firsthear.getSelectedItem().toString();
                final String hearcount = hear_count.getSelectedItem().toString();
                final String joinanyprogram_start = join_anyprogram_start.getSelectedItem().toString();
                final String joinanyprogram_end = join_anyprogram_end.getSelectedItem().toString();
                final String financial_ = financial.getText().toString();

                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = current_user.getUid();

                db_ds = FirebaseDatabase.getInstance().getReference().child("Digital Sakhi").child(uid);
                Map<String ,Object> usermap = new HashMap<>();
                usermap.put("06:How familiar were you with LTFS ?",ltfsfamilier);
                usermap.put("07:When did you first hear about LTFS ?",ltfsfirsthear);
                usermap.put("08:When you think of financial loans, what brand comes to your mind ?",financial_);
                usermap.put("09:How ofter do you hear people talking about LTFS ?",hearcount);
                usermap.put("10:How likely are you to join any program run by LTFS ? (Beginning of the Program)",joinanyprogram_start);
                usermap.put("11:To what extent would you recommend LTFS to anyone ? (End of the Program)",joinanyprogram_end);
                db_ds.updateChildren(usermap);

                Toast.makeText(EN_ds_brand_awarness.this,"Saved",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(EN_ds_brand_awarness.this,EN_leadership.class);
                startActivity(i);

            }
        });
    }
}
