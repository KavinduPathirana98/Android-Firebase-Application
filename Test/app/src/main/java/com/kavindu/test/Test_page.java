package com.kavindu.test;
/**/

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Test_page extends AppCompatActivity {
    EditText txt_username, txt_password, txt_name, txt_phoneno, txt_email;
    Button btn_click;
    String dp = "125dp";


    public void Collect_usernames(Map<String, Object> users) {

        ArrayList<String> phoneNumbers = new ArrayList<>();

        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()) {

            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
            phoneNumbers.add((String) singleUser.get("username"));
            //create dynamic button

        }
        int si = phoneNumbers.size();
        for (int c = 0; c < si; c++) {
            final Button btn = new Button(getApplicationContext());
            btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            btn.setText(phoneNumbers.get(c));
            btn.setId(c);
            ll.addView(btn);
            final TextView textView = new TextView(getApplicationContext());
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textView.setText("Fuck Android");
            ll.addView(textView);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = btn.getText().toString();
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users").child(name);
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChildren()) {

                                txt_username.setText(snapshot.child("username").getValue().toString());
                                txt_password.setText(snapshot.child("password").getValue().toString());
                                txt_name.setText(snapshot.child("name").getValue().toString());
                                txt_phoneno.setText(snapshot.child("phoneno").getValue().toString());
                                txt_email.setText(snapshot.child("email").getValue().toString());
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    Toast.makeText(Test_page.this, "Button" + v.getId() + "Clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }


        System.out.println(phoneNumbers.toString());
        // System.out.println(phoneNumbers);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_page);
        txt_username = findViewById(R.id.txt_username);
        txt_email = findViewById(R.id.txt_email);
        txt_name = findViewById(R.id.txt_name);
        txt_password = findViewById(R.id.txt_password);
        txt_phoneno = findViewById(R.id.txt_phone);
        btn_click = findViewById(R.id.btn_click);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count = (int) snapshot.getChildrenCount();
                Collect_usernames((Map<String, Object>) snapshot.getValue());



         /* for (int c=1;c<=count;c++)
                {
                    Button btn=new Button(getApplicationContext());
                    btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    btn.setText("HI HOW "+c);
                    btn.setId(c);
                    ll.addView(btn);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(Test_page.this,"Button"+v.getId()+"Clicked",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
               */
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

      /*  reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    //String username = snapshot.child("username").getValue().toString();
                    //String password = snapshot.child("password").getValue().toString();
                    txt_username.setText(snapshot.child("username").getValue().toString());
                    txt_password.setText(snapshot.child("password").getValue().toString());
                    txt_name.setText(snapshot.child("name").getValue().toString());
                    txt_phoneno.setText(snapshot.child("phoneno").getValue().toString());
                    txt_email.setText(snapshot.child("email").getValue().toString());


                } else {
                    Toast.makeText(getApplicationContext(), "Check Username and Password!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


    }

    public void create_dynamic_button() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        for (int i = 0; i < 4; i++) {
            Button btn = new Button(this);
            btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            btn.setText("HI HOW " + i);
            btn.setId(i);
            ll.addView(btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Test_page.this, "Button" + v.getId() + "Clicked", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }
}