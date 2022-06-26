package com.kavindu.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class Seller_Dashboard extends AppCompatActivity {
    CardView add;
    TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller__dashboard);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        add = findViewById(R.id.card_add);
        user = findViewById(R.id.txt_username);
        get_username();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name=user.getText().toString();
                Intent intent = new Intent(getApplicationContext(), Add_Job.class);
                intent.putExtra("username",user_name);
                startActivity(intent);
            }
        });
    }


    private void get_username() {
        Bundle extras=getIntent().getExtras();
        String username=extras.getString("username");
        user.setText(username);

    }
}