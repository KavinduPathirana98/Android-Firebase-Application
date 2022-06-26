package com.kavindu.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Seller_Login extends AppCompatActivity {
    TextInputLayout txt_username, txt_password;
    Button btn_login, btn_signup;
    ImageView back_button;
    TextView lbl_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller__login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        back_button = findViewById(R.id.back_button);
        btn_login = findViewById(R.id.btn_login);
        txt_username = findViewById(R.id.txt_username);
        txt_password = findViewById(R.id.txt_password);
        lbl_error = findViewById(R.id.lbl_error);
        btn_signup = findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirect_signup_page();
            }
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirect_main_page();

            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();

            }
        });

    }
    public void validate() {

        String user_input_username = txt_username.getEditText().getText().toString();
        final String user_input_password = txt_password.getEditText().getText().toString();
        txt_username.setError(null);
        txt_password.setError(null);

        if (user_input_username.isEmpty()) {
            txt_username.setError("Field Cannot Empty!");

        } else if (user_input_password.isEmpty()) {
            txt_password.setError("Field Cannot Empty!");

        } else {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("sellers").child(user_input_username);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChildren()) {
                        String username = snapshot.child("username").getValue().toString();
                        String password = snapshot.child("password").getValue().toString();
                        String email=snapshot.child("email").getValue().toString();
                        String name=snapshot.child("name").getValue().toString();
                        String phoneno=snapshot.child("phoneno").getValue().toString();
                        if (password.equals(user_input_password)) {
                            Intent intent=new Intent(getApplicationContext(),Seller_Dashboard.class);
                            intent.putExtra("password",password);
                            intent.putExtra("username",username);
                            intent.putExtra("email",email);
                            intent.putExtra("name",name);
                            intent.putExtra("phoneno",phoneno);
                            startActivity(intent);
                            // opendashboard();
                        } else {
                            lbl_error.setText("Check  Password !!");
                        }

                    } else {
                        //Toast.makeText(getApplicationContext(), "Check Username and Password!!", Toast.LENGTH_LONG).show();
                        lbl_error.setText("Check Username !!");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

    }
    void redirect_signup_page()
    {
        Intent intent=new Intent(this,Seller_Signup.class);
        startActivity(intent);
    }
    void redirect_main_page() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}