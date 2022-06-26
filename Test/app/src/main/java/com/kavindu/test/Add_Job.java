package com.kavindu.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Add_Job extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String cat_name, item;
    TextInputLayout txt_regname, txt_expirience, txt_descripion, txt_regphoneno, txt_map;
    Button btn_reg;
    TextView lbl_welcome,lbl_user;
    ImageView back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__job);
        Spinner spinner = (Spinner) findViewById(R.id.catgory);
        btn_reg = findViewById(R.id.btn_create);
        back_button = findViewById(R.id.back_button);
        txt_regname = findViewById(R.id.job_title);
        txt_expirience = findViewById(R.id.txt_expirience);
        txt_descripion = findViewById(R.id.txt_description);
        txt_regphoneno = findViewById(R.id.txt_phone);
        txt_map = findViewById(R.id.txt_map);
        lbl_welcome=findViewById(R.id.lbl_welcome);
        lbl_user=findViewById(R.id.username);
        get_username();

        List<String> categories = new ArrayList<String>();
        categories.add("Information Technology");
        categories.add("Marketing");
        categories.add("Engineering");
        categories.add("Finance");
        categories.add("Design");
        categories.add("Logistics");

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valiate();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item

        item = parent.getItemAtPosition(position).toString();
        if (item.equals("Information Technology")) {
            cat_name = "IT";

        } else if (item.equals("Marketing")) {
            cat_name = "MAR";

        } else if (item.equals("Engineering")) {
            cat_name = "ENG";

        } else if (item.equals("Finance")) {
            cat_name = "FIN";

        } else if (item.equals("Design")) {
            cat_name = "DES";


        } else if (item.equals("Logistic")) {
            cat_name = "LOG";

        }


        // Showing selected spinner item

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public boolean valiate() {

        //get all the values
        String name = txt_regname.getEditText().getText().toString();
        String expirience = txt_expirience.getEditText().getText().toString();
        String username=lbl_user.getText().toString();
        String description = txt_descripion.getEditText().getText().toString();
        String phone = txt_regphoneno.getEditText().getText().toString();
        String map = txt_map.getEditText().getText().toString();


        //clear error if message exist
        txt_regname.setError(null);
        txt_expirience.setError(null);
        txt_descripion.setError(null);
        txt_regphoneno.setError(null);
        txt_map.setError(null);

        //Check Validations
        if (name.isEmpty()) {
            txt_regname.setError("User name is Empty !! ");
            return false;
        } /*else if (username.isEmpty()) {
            txt.setError("Username is empty !!");
            return false;
        } */else if (description.isEmpty()) {
            txt_descripion.setError("E-mail is empty !!");
            return false;
        } else if (phone.isEmpty()) {
            txt_regphoneno.setError("Phone Number is empty !!");
            return false;
        } else if (map.isEmpty()) {
            txt_map.setError("Password is empty!!");
            return false;
        }
        else if (expirience.isEmpty()) {
            txt_expirience.setError("Password is empty!!");
            return false;
        } /*else if (username.length() > 15) {
            regusername.setError("Length should be less than 15 !");
            return false;
        } else if (password.length() < 8) {
            regpassword.setError("Password Length is greater!");
            return false;
        } */else {
            DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child(cat_name).child(name);
            reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChildren()) {
                        txt_regname.setError("Job Name Already Exist!!");
                    } else {
                        String name = txt_regname.getEditText().getText().toString();
                        String expirience = txt_expirience.getEditText().getText().toString();
                        String username=lbl_user.getText().toString();
                        String description = txt_descripion.getEditText().getText().toString();
                        String phone = txt_regphoneno.getEditText().getText().toString();
                        String map = txt_map.getEditText().getText().toString();
                        FirebaseDatabase rootNode;
                        DatabaseReference reference;
                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference(cat_name).child(name);
                        job helperClass = new job(name,expirience,username,phone,description,map);
                        reference.setValue(helperClass);

                        //Successfull msg
                        Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_LONG).show();
                        //clear all text
                        txt_regname.getEditText().setText("");
                        txt_descripion.getEditText().setText("");
                        txt_expirience.getEditText().setText("");
                        txt_map.getEditText().setText("");
                        txt_regphoneno.getEditText().setText("");


                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
        return true;
    }
    private void get_username() {
        Bundle extras=getIntent().getExtras();
        String username=extras.getString("username");
        lbl_user.setText(username);

    }
}