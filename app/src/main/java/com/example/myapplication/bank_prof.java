package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

public class bank_prof extends Activity {
TextView email,name,bank,br,ifsc;
Button gd;
SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_prof);
        final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
        email= findViewById(R.id.textView41);
        email.setText(globalvariabel.GetUsername().toString());
        gd=(Button)findViewById(R.id.button26);
        name= findViewById(R.id.textView45);
        bank= findViewById(R.id.textView46);
        br= findViewById(R.id.textView48);
        ifsc= findViewById(R.id.textView50);
        db=openOrCreateDatabase("ecil", Context.MODE_PRIVATE, null);
        db.execSQL("create table if not exists breg(bank varchar,branch varchar,ifsc varchar, name varchar, email varchar,pass varchar);");
        gd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c=db.rawQuery("SELECT * FROM breg WHERE email='"+email.getText()+"'", null);
                if(c.moveToFirst())
                {
                    name.setText(c.getString(3));
                    bank.setText(c.getString(0));
                    br.setText(c.getString(1));
                    ifsc.setText(c.getString(2));
                }
            }
        });
    }
}
