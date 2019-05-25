package com.example.dell.chaitanya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ProjectActivity extends AppCompatActivity {

    final String EN_ID = getIntent().getStringExtra("enroll");
    Toolbar toolbar;
    Button livelihood,fwwb,ds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        toolbar = (Toolbar)findViewById(R.id.toolbar_project);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Project Selection");

        livelihood = (Button)findViewById(R.id.livelihood_development);
        fwwb = (Button)findViewById(R.id.fwwb_programme);
        ds = (Button)findViewById(R.id.digital_sakhi);

        livelihood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProjectActivity.this,EN_generalinfo.class);
                i.putExtra("enroll",EN_ID);
                startActivity(i);
                finish();
            }
        });

        ds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProjectActivity.this,EN_ds_basic.class);
                startActivity(i);
                finish();
            }
        });

        fwwb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProjectActivity.this,EN_fwwbbasic.class);
                i.putExtra("enroll",EN_ID);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.log_out){
            FirebaseAuth.getInstance().signOut();
            Intent startintent = new Intent(ProjectActivity.this,Login.class);
            startActivity(startintent);
            finish();
        }
        return true;
    }
}
