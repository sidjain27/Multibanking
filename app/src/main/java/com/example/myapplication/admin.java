package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class admin extends AppCompatActivity {
    Button lo,ar,tr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        lo = findViewById(R.id.button14);
        lo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin.this, MainActivity.class));
            }
        });
        ar = findViewById(R.id.button12);
        ar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin.this,admin_req.class));
            }
        });
        tr = findViewById(R.id.button13);
        tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin.this, admin_trans.class));
            }
        });
    }
        @Override
        public void onBackPressed()
        {
            Toast.makeText(getApplicationContext(),"Logout to go back!",Toast.LENGTH_SHORT).show();
        }
    }
