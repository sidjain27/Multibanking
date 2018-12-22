package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class cust_home extends AppCompatActivity {
    Button lo,quer,sr,tr;
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_home);
        final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
        name= findViewById(R.id.textView21);
        name.setText(globalvariabel.GetUsername().toString());
        lo = findViewById(R.id.button9);
        lo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(cust_home.this, MainActivity.class));
            }
        });
        quer = findViewById(R.id.button8);
        quer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(cust_home.this, send_query.class));
            }
        });
        sr = findViewById(R.id.button6);
        sr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(cust_home.this, cust_req.class));
            }
        });
        tr = findViewById(R.id.button7);
        tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(cust_home.this, Send_money.class));
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        Toast.makeText(getApplicationContext(),"Logout to go back!",Toast.LENGTH_SHORT).show();
    }
}

