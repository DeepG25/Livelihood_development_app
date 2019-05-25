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

public class EN_Livelihood_scheme extends AppCompatActivity {

    Spinner mushroom_cultivation,vermi_compost,plantation_plants,vegetable_development,ccp_culture,nimark,seed_supply,supply_rayzobium,supply_plant,support_sericulture,hoticulture,support_nursery,polly_pack,work_mgnrega,
            training_women,fishery,goatry,dairy,poultry,shilai_training,special_component,agri_implement,bee_keeping,source_info,tried,if_yes_reasons;
    EditText other;
    Button next;
    Toolbar toolbar;
    TextView enroll_no;
    enroll en;

    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en__livelihood_scheme);

        toolbar = (Toolbar)findViewById(R.id.toolbar_elivelihood_scheme);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Livelihood Schemes");

        mushroom_cultivation = (Spinner)findViewById(R.id.mushroom_cultivation_spinner);
        vermi_compost = (Spinner)findViewById(R.id.vermi_compost_spinner);
        plantation_plants = (Spinner)findViewById(R.id.plantation_plants_spinner);
        vegetable_development = (Spinner)findViewById(R.id.vegetable_development_spinner);
        ccp_culture = (Spinner)findViewById(R.id.ccp_culture_spinner);
        nimark = (Spinner)findViewById(R.id.nimark_spinner);
        seed_supply = (Spinner)findViewById(R.id.seed_supply_spinner);
        supply_rayzobium = (Spinner)findViewById(R.id.supply_rayzobium_spinner);
        supply_plant = (Spinner)findViewById(R.id.supply_plant_spinner);
        support_sericulture = (Spinner)findViewById(R.id.support_sericulture_spinner);
        hoticulture = (Spinner)findViewById(R.id.hoticulture_spinner);
        support_nursery = (Spinner)findViewById(R.id.support_nursery_spinner);
        polly_pack = (Spinner)findViewById(R.id.polly_pack_spinner);
        work_mgnrega = (Spinner)findViewById(R.id.work_mgnrega_spinner);
        training_women = (Spinner)findViewById(R.id.training_women_spinner);
        fishery = (Spinner)findViewById(R.id.fishery_spinner);
        goatry = (Spinner)findViewById(R.id.goatry_spinner);
        dairy = (Spinner)findViewById(R.id.dairy_spinner);
        poultry = (Spinner)findViewById(R.id.poultry_spinner);
        shilai_training = (Spinner)findViewById(R.id.shilai_training_spinner);
        special_component = (Spinner)findViewById(R.id.special_component_spinner);
        agri_implement = (Spinner)findViewById(R.id.agri_implement_spinner);
        bee_keeping = (Spinner)findViewById(R.id.bee_keeping_spinner);
        source_info = (Spinner)findViewById(R.id.source_info_spinner);
        tried = (Spinner)findViewById(R.id.tried_spinner);
        if_yes_reasons = (Spinner)findViewById(R.id.if_yes_reasons_spinner);
        other = (EditText)findViewById(R.id.other_et);
        next = (Button)findViewById(R.id.next10_btn);

        enroll_no = (TextView)findViewById(R.id.enrollment_id_number8);
        enroll_no.setText(en.getEnroll_id());

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mushroomcultivation = mushroom_cultivation.getSelectedItem().toString();
                final String vermicompost = vermi_compost.getSelectedItem().toString();
                final String plantationplants = plantation_plants.getSelectedItem().toString();
                final String vegetabledevelopment = vegetable_development.getSelectedItem().toString();
                final String ccpculture = ccp_culture.getSelectedItem().toString();
                final String nimark_ = nimark.getSelectedItem().toString();
                final String seedsupply = seed_supply.getSelectedItem().toString();
                final String supplyrayzobium = supply_rayzobium.getSelectedItem().toString();
                final String supplyplant = supply_plant.getSelectedItem().toString();
                final String supportsericulture = support_sericulture.getSelectedItem().toString();
                final String hoticulture_ = hoticulture.getSelectedItem().toString();
                final String supportnursery = support_nursery.getSelectedItem().toString();
                final String pollypack = polly_pack.getSelectedItem().toString();
                final String workmgnrega = work_mgnrega.getSelectedItem().toString();
                final String trainingwomen = training_women.getSelectedItem().toString();
                final String fishery_ = fishery.getSelectedItem().toString();
                final String goatry_ = goatry.getSelectedItem().toString();
                final String dairy_ = dairy.getSelectedItem().toString();
                final String poultry_ = poultry.getSelectedItem().toString();
                final String shilaitraining = shilai_training.getSelectedItem().toString();
                final String specialcomponent = special_component.getSelectedItem().toString();
                final String agriimplement = agri_implement.getSelectedItem().toString();
                final String beekeeping = bee_keeping.getSelectedItem().toString();
                final String sourceinfo = source_info.getSelectedItem().toString();
                final String tried_ = tried.getSelectedItem().toString();
                final String if_yesreasons = if_yes_reasons.getSelectedItem().toString();
                final String other_ = other.getText().toString();

                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                assert current_user != null;
                String uid = current_user.getUid();
                db = FirebaseDatabase.getInstance().getReference().child("Livelihood Programme").child(uid);

                Map<String ,Object> usermap = new HashMap<>();
                usermap.put("090:Mushroom Cultivation Unit",mushroomcultivation);
                usermap.put("091:Vermi Compost Preperation Unit",vermicompost);
                usermap.put("092:Plantation of Medicinal Plants",plantationplants);
                usermap.put("093:Vegetable Devlopment Scheme",vegetabledevelopment);
                usermap.put("094:C C P Culture Preparation Unit",ccpculture);
                usermap.put("095:Nimark Preparation Scheme",nimark_);
                usermap.put("096:Seed Supply of Green Manure Crops",seedsupply);
                usermap.put("097:Supply of Rayzobium and PSB",supplyrayzobium);
                usermap.put("098:Supply of Plant Protection Measures",supplyplant);
                usermap.put("099:Support of Sericulture Unit",supportsericulture);
                usermap.put("100:Hoticulture Plantation (RKVY and NHM)",hoticulture_);
                usermap.put("101:Support for Nursery Unit",supportnursery);
                usermap.put("102:Polly, Pack House and Shed net",pollypack);
                usermap.put("103:Work under MGNREGA",workmgnrega);
                usermap.put("104:Training to Women for Enterprise",trainingwomen);
                usermap.put("105:Fishery",fishery_);
                usermap.put("106:Goatry Unit",goatry_);
                usermap.put("107:Dairy Unit",dairy_);
                usermap.put("108:Poultry Mother Unit",poultry_);
                usermap.put("109:Shilai Machine for Tailoring",shilaitraining);
                usermap.put("110:Special Component Program",specialcomponent);
                usermap.put("111:Agri Implements Scheme for Farmers",agriimplement);
                usermap.put("112:Bee Keeping Unit",beekeeping);
                usermap.put("113:Other",other_);
                usermap.put("114:Source of Infomation regarding various Govt scheme?",sourceinfo);
                usermap.put("115:Have you tried to get above schemes, but did not get benifits from it",tried_);
                usermap.put("116:If YES, reason",if_yesreasons);
                db.updateChildren(usermap);

                Toast.makeText(EN_Livelihood_scheme.this,"Saved",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(EN_Livelihood_scheme.this,EN_agriculture_inputs.class);
                startActivity(i);

            }
        });
    }
}
