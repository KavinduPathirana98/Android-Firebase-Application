package com.kavindu.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button login, signup, seller;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.btn_login);
        signup = findViewById(R.id.btn_signup);
        seller = findViewById(R.id.btn_seller);

        seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),Seller_Login.class);
                startActivity(intent);
            }
        });

    }
    public void redirect_login(View view)
    {
        Intent intent =new Intent(getApplicationContext(),Login_Page.class);

        Pair[]pairs=new Pair[1];

        pairs[0]=new Pair<View,String>(login,"transition_login");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
            startActivity(intent,options.toBundle());
        }
        else
        {
            startActivity(intent);
        }

    }
    public  void redirect_signup(View view)
    {
        Intent intent =new Intent(getApplicationContext(),Signup_Page.class);

        Pair[]pairs=new Pair[1];

        pairs[0]=new Pair<View,String>(signup,"transition_signup");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
            startActivity(intent,options.toBundle());
        }
        else
        {
            startActivity(intent);
        }

    }
}