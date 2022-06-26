package com.kavindu.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {
TextView lbl_name,lbl_usrname,lbl_phone,lbl_password,lbl_email;
CardView it,mar,eng,fin,log,des;
String value,job_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        it=findViewById(R.id.card_IT);
        mar=findViewById(R.id.card_MAR);
        eng=findViewById(R.id.card_ENG);
        fin=findViewById(R.id.card_FIN);
        log=findViewById(R.id.card_LOG);
        des=findViewById(R.id.card_DES);

        it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
click_it();
            }
        });
        mar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_mar();
            }
        });
        eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_eng();
            }
        });
        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_fin();
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_log();
            }
        });
        des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_des();
            }
        });





    }
    private void get_All_Data()
    {
        Intent intent=getIntent();
        String username=intent.getStringExtra("username");
        String name=intent.getStringExtra("name");
        String email=intent.getStringExtra("email");
        String password=intent.getStringExtra("password");
        String phoneno=intent.getStringExtra("phoneno");

        lbl_usrname.setText(username.toString());
        lbl_name.setText(name.toString());
        lbl_email.setText(email.toString());
        lbl_password.setText(password.toString());
        lbl_phone.setText(phoneno.toString());
    }
    private void click_it()
    {
        value="IT";
        job_title="Information Technology";
        Intent intent=new Intent(getApplicationContext(),Job_List.class);
        intent.putExtra("Category",value);
        intent.putExtra("job_title",job_title);
        startActivity(intent);
    }
    private void click_mar()
    {
        value="MAR";
        job_title="Marketing";
        Intent intent=new Intent(getApplicationContext(),Job_List.class);
        intent.putExtra("Category",value);
        intent.putExtra("job_title",job_title);
        startActivity(intent);
    }
    private void click_des()
    {
        value="DES";
        job_title="Designing";
        Intent intent=new Intent(getApplicationContext(),Job_List.class);
        intent.putExtra("Category",value);
        intent.putExtra("job_title",job_title);
        startActivity(intent);
    }
    private void click_fin()
    {
        value="FIN";
        job_title="Financial";
        Intent intent=new Intent(getApplicationContext(),Job_List.class);
        intent.putExtra("Category",value);
        intent.putExtra("job_title",job_title);
        startActivity(intent);
    }
    private void click_eng()
    {
        value="ENG";
        job_title="Engineering";
        Intent intent=new Intent(getApplicationContext(),Job_List.class);
        intent.putExtra("Category",value);
        intent.putExtra("job_title",job_title);
        startActivity(intent);
    }
    private void click_log()
    {
        value="LOG";
        job_title="Logistic";
        Intent intent=new Intent(getApplicationContext(),Job_List.class);
        intent.putExtra("Category",value);
        intent.putExtra("job_title",job_title);
        startActivity(intent);
    }
}