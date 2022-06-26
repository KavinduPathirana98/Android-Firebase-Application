package com.kavindu.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Detail_Page extends AppCompatActivity {
    String job_title,description,expirience,contact,map, value;
    TextView job_titile, Description, Expirience, Contact, Map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__page);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Bundle extras=getIntent().getExtras();
        job_title=extras.getString("Job Title");
        description=extras.getString("Description");
        expirience=extras.getString("Expirience");
        contact=extras.getString("Contact");
        map=extras.getString("Map");


       // Intent intent = new Intent();
        //job_name = intent.getStringExtra("Job Title");
        //value = intent.getStringExtra("value");

        job_titile = findViewById(R.id.txt_Job_Title);
        Description = findViewById(R.id.txt_Description);
        Expirience = findViewById(R.id.txt_Expirience);
        Contact = findViewById(R.id.txt_Contact);
        Map = findViewById(R.id.txt_Location);
        job_titile.setText(job_title);
        Description.setText(description);
        Expirience.setText(expirience);
        Contact.setText(contact);
        Map.setText(map);

        Map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(Map.getText().toString()); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


    }

}