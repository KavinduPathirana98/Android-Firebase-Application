package com.kavindu.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Signup_Page extends AppCompatActivity {
    TextInputLayout regname, regusername, regemail, regphoneno, regpassword;
    Button btn_reg, btn_regtologin;
    ImageView back_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__page);

        btn_reg = findViewById(R.id.btn_register);
        back_button = findViewById(R.id.back_button);
        regname = findViewById(R.id.txt_name);
        regusername = findViewById(R.id.txt_username);
        regemail = findViewById(R.id.txt_email);
        regphoneno = findViewById(R.id.txt_phone);
        regpassword = findViewById(R.id.txt_password);
        btn_regtologin = findViewById(R.id.btn_redirect_login);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                valiate();
            }
        });
    }


    public void back() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public boolean valiate() {

        //get all the values
        String name = regname.getEditText().getText().toString();
        String username = regusername.getEditText().getText().toString();
        String email = regemail.getEditText().getText().toString();
        String phone = regphoneno.getEditText().getText().toString();
        String password = regpassword.getEditText().getText().toString();



        //clear error if message exist
        regusername.setError(null);
        regpassword.setError(null);
        regemail.setError(null);
        regphoneno.setError(null);
        regname.setError(null);

        //Check Validations
        if (name.isEmpty()) {
            regname.setError("User name is Empty !! ");
            return false;
        } else if (username.isEmpty()) {
            regusername.setError("Username is empty !!");
            return false;
        } else if (email.isEmpty()) {
            regemail.setError("E-mail is empty !!");
            return false;
        } else if (phone.isEmpty()) {
            regphoneno.setError("Phone Number is empty !!");
            return false;
        } else if (password.isEmpty()) {
            regpassword.setError("Password is empty!!");
            return false;
        } else if (username.length() > 15) {
            regusername.setError("Length should be less than 15 !");
            return false;
        } else if (password.length() < 8) {
            regpassword.setError("Password Length is greater!");
            return false;
        } else {
            DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("users").child(username);
            reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChildren())
                    {
                        regusername.setError("Username Already Exist!!");
                    }
                    else
                    {
                        String name = regname.getEditText().getText().toString();
                        String username = regusername.getEditText().getText().toString();
                        String email = regemail.getEditText().getText().toString();
                        String phone = regphoneno.getEditText().getText().toString();
                        String password = regpassword.getEditText().getText().toString();

                        FirebaseDatabase rootNode;
                        DatabaseReference reference;
                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference("users");
                        UserHelperClass helperClass = new UserHelperClass(name, username, email, phone, password);
                        reference.child(username).setValue(helperClass);

                        //Successfull msg
                        Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_LONG).show();
                        //clear all text
                        regpassword.getEditText().setText("");
                        regname.getEditText().setText("");
                        regusername.getEditText().setText("");
                        regemail.getEditText().setText("");
                        regphoneno.getEditText().setText("");
                        //reload page
                        setContentView(R.layout.activity_signup__page);
                        Intent intent=new Intent(getApplicationContext(),Login_Page.class);
                        startActivity(intent);

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
        return true;
    }
}

