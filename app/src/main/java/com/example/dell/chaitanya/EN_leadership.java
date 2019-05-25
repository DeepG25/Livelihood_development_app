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

public class EN_leadership extends AppCompatActivity {

    Spinner new_sit,feel_con,confident,positive,empathize,resouces,opportunity,interest,assign_tasks,calm,decision,limitations;
    Button next;
    Toolbar toolbar;
    private DatabaseReference db_ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_leadership);

        toolbar = (Toolbar)findViewById(R.id.toolbar_ds_leadership);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Leadership Measurement");

        new_sit = (Spinner)findViewById(R.id.new_sit_spinner_ds);
        feel_con = (Spinner)findViewById(R.id.feel_con_spinner_ds);
        confident = (Spinner)findViewById(R.id.confident_spinner_ds);
        positive = (Spinner)findViewById(R.id.positive_spinner_ds);
        empathize = (Spinner)findViewById(R.id.empathize_spinner_ds);
        resouces = (Spinner)findViewById(R.id.resouces_spinner_ds);
        opportunity = (Spinner)findViewById(R.id.opportunity_spinner_ds);
        interest = (Spinner)findViewById(R.id.interest_spinner_ds);
        assign_tasks = (Spinner)findViewById(R.id.assign_tasks_spinner_ds);
        calm = (Spinner)findViewById(R.id.calm_spinner_ds);
        decision = (Spinner)findViewById(R.id.decision_spinner_ds);
        limitations = (Spinner)findViewById(R.id.limitations_spinner_ds);
        next = (Button)findViewById(R.id.next2_btn_ds);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String newsit = new_sit.getSelectedItem().toString();
                final String feelcon = feel_con.getSelectedItem().toString();
                final String confident_ = confident.getSelectedItem().toString();
                final String positive_ = positive.getSelectedItem().toString();
                final String empathize_ = empathize.getSelectedItem().toString();
                final String resouces_ = resouces.getSelectedItem().toString();
                final String opportunity_ = opportunity.getSelectedItem().toString();
                final String interest_ = interest.getSelectedItem().toString();
                final String assigntasks = assign_tasks.getSelectedItem().toString();
                final String calm_ = calm.getSelectedItem().toString();
                final String decision_ = decision.getSelectedItem().toString();
                final String limitations_ = limitations.getSelectedItem().toString();

                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = current_user.getUid();

                db_ds = FirebaseDatabase.getInstance().getReference().child("Digital Sakhi").child(uid);
                Map<String ,Object> usermap = new HashMap<>();
                usermap.put("12:When you are confronted with new situations, how often do you feel you can cope up with it?",newsit);
                usermap.put("13:How often do you feel confortable in making tough decision?",feelcon);
                usermap.put("14:How often are you confident in negotiation skills across different setting?",confident_);
                usermap.put("15:When pursuing a goal, how often do you maintain a positive, focused attitude, despite obstacles?",positive_);
                usermap.put("16:How often do you empathize with other people's needs, concerns, and goals?",empathize_);
                usermap.put("17:Do you know where to turn for the resouces you need ?",resouces_);
                usermap.put("18:When you see an opportunity for growth of you or family or team, do you take the initiative to grab the opportunity?",opportunity_);
                usermap.put("19:If anything is in the best interest of your team or family will you take the risk ?",interest_);
                usermap.put("20:In a group activity, can you effectively assign tasks to people (on the basis of their strengths)?",assigntasks);
                usermap.put("21:In situations that are full of turnroll and confusion, do you stay calm and level headed?",calm_);
                usermap.put("22:How confortable are you in decision making?",decision_);
                usermap.put("23:Do you have an accurate understanding of your own limitations?",limitations_);
                db_ds.updateChildren(usermap);

                Toast.makeText(EN_leadership.this,"Saved",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(EN_leadership.this,EN_ds_financial.class);
                startActivity(i);
            }
        });
    }
}
