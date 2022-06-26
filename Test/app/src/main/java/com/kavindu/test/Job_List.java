package com.kavindu.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import static com.kavindu.test.R.color.light_blue_70;

public class Job_List extends AppCompatActivity {
    String value;
    String job_name;
    TextView lbl_value;
    String btn_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job__list);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        lbl_value = findViewById(R.id.txt_topic);
        get_value();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(value);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                collect_names((Map<String, Object>) snapshot.getValue());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void get_value() {
        Intent intent = getIntent();
        value = intent.getStringExtra("Category");
        job_name=intent.getStringExtra("job_title");
        lbl_value.setText(job_name);

    }


    public void collect_names(Map<String, Object> IT) {
        final LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        ArrayList<String> job_titles = new ArrayList<>();
        for (Map.Entry<String, Object> entry : IT.entrySet()) {
            //get job map
            Map single_job = (Map) entry.getValue();
            //get jjob title
            job_titles.add((String) single_job.get("job_Title"));


        }
        //Dynamic Layout
        int size = job_titles.size();
        for (int count = 0; count < size; count++) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            final Button btn = new Button(getApplicationContext());
            btn.setLayoutParams(layoutParams);
            btn.setText(job_titles.get(count));
            layoutParams.setMargins(10, 10, 10, 10);
            btn.setId(count);
            btn.setBackgroundColor(Color.parseColor("#9999ff"));

            ll.addView(btn);
            final int finalCount = count;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    btn_name = btn.getText().toString();
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(value).child(btn_name);
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            // code to get detsail from the databse which equal to button's value
                            if (snapshot.hasChildren()) {
                                String Job_Titile_value = snapshot.child("job_Title").getValue().toString();
                                String Description_value = snapshot.child("description").getValue().toString();
                                String Expirience_value = snapshot.child("expirience").getValue().toString();
                                String Contact_value = snapshot.child("phone").getValue().toString();
                                String Map_value = snapshot.child("map").getValue().toString();
                                Intent intent = new Intent(Job_List.this, Detail_Page.class);
                                intent.putExtra("Job Title", Job_Titile_value);
                                intent.putExtra("Description", Description_value);
                                intent.putExtra("Expirience", Expirience_value);
                                intent.putExtra("Contact", Contact_value);
                                intent.putExtra("Map", Map_value);
                                startActivity(intent);


                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
            });


        }

    }

    private void click() {

        //intent.putExtra("value", value);

    }

}