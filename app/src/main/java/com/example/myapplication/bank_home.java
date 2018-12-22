package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class bank_home extends AppCompatActivity {
    Button lo,prof,tr,quer;
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_home);
        final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
        name= findViewById(R.id.textView20);
        name.setText(globalvariabel.GetUsername().toString());
        prof=findViewById(R.id.button18);
        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(bank_home.this,bank_prof.class));
            }
        });
        lo = findViewById(R.id.button11);
        lo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(bank_home.this, MainActivity.class));
            }
        });
        tr = findViewById(R.id.button23);
        tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(bank_home.this, bank_trans.class));
            }
        });
        quer=findViewById(R.id.button10);
        quer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(bank_home.this,bank_query.class));
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        Toast.makeText(getApplicationContext(),"Logout to go back!",Toast.LENGTH_SHORT).show();
    }
}
