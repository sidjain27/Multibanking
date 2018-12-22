package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.Spinner;
import android.widget.Toast;

public class customer_reg extends AppCompatActivity {
        EditText name,dob,email,phno,pass;
        Button sub;
        SQLiteDatabase db;
        Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_reg);
        name=(EditText)findViewById(R.id.editText6);
        dob=(EditText)findViewById(R.id.editText4);
        phno=(EditText)findViewById(R.id.editText8);
        sp=(Spinner)findViewById(R.id.spinner1);
        email=(EditText)findViewById(R.id.editText5);
        pass=(EditText)findViewById(R.id.editText9);
        sub=(Button)findViewById(R.id.button);
        db=openOrCreateDatabase("ecil", Context.MODE_PRIVATE, null);
        db.execSQL("create table if not exists creg(name varchar,birthdate varchar,phone varchar,bank varchar,email varchar,pass varchar);");
        sub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(name.getText().toString().trim().length()==0||
                        dob.getText().toString().trim().length()==0||
                        email.getText().toString().trim().length()==0||
                        phno.getText().toString().trim().length()==0||
                        pass.getText().toString().trim().length()==0)
                {
                    Toast.makeText(getApplicationContext(), "Enter All the Fields", Toast.LENGTH_SHORT).show();
                    return;

                }
                else if(phno.getText().toString().trim().length()!=10)
                {
                    Toast.makeText(getApplicationContext(), "Please Enter 10 Digit Mobile No", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    db.execSQL("insert into creg values('"+name.getText()+"','"+ dob.getText()+"','"+phno.getText()+"','"+sp.getSelectedItemId()+"','"+email.getText()+"','"+pass.getText()+"');");
                    Toast.makeText(getApplicationContext(), "Registration Successful!", Toast.LENGTH_SHORT).show();
                    clear();
                    Intent a = new Intent(customer_reg.this, LoginActivity2.class);
                    startActivity(a);
                    return;
                }
            }
        });
     }
    public void clear()
    {
        name.setText("");
        dob.setText("");
        email.setText("");
        phno.setText("");
        pass.setText("");

    }
}