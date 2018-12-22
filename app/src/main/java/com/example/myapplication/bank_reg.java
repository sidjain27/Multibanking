package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class bank_reg extends AppCompatActivity {
        EditText br,ifsc,nam,email,pass;
        Button sub;
        SQLiteDatabase db;
        Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_reg);
        sp=(Spinner)findViewById(R.id.spinner1);
        br=(EditText)findViewById(R.id.editText);
        ifsc=(EditText)findViewById(R.id.editText2);
        nam=(EditText)findViewById(R.id.editText3);
        email=(EditText)findViewById(R.id.editText12);
        pass=(EditText)findViewById(R.id.editText7);
        sub=(Button)findViewById(R.id.button);
        db=openOrCreateDatabase("ecil", Context.MODE_PRIVATE, null);
        db.execSQL("create table if not exists breg(bank varchar,branch varchar,ifsc varchar, name varchar, email varchar,pass varchar);");
        sub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(br.getText().toString().trim().length()==0||
                        ifsc.getText().toString().trim().length()==0||
                        nam.getText().toString().trim().length()==0||
                        email.getText().toString().trim().length()==0||
                        pass.getText().toString().trim().length()==0)
                {
                    Toast.makeText(getApplicationContext(), "Enter All the Fields", Toast.LENGTH_SHORT).show();
                    return;

                }
                else {
                    db.execSQL("insert into breg values('"+sp.getSelectedItem()+"','"+br.getText()+"','"+ ifsc.getText()+"','"+nam.getText()+"','"+email.getText()+"','"+pass.getText()+"');");
                    Toast.makeText(getApplicationContext(), "Registration Successful!", Toast.LENGTH_SHORT).show();
                    clear();
                    Intent a = new Intent(bank_reg.this, LoginActivity2.class);
                    startActivity(a);
                    return;
                }
            }
        });
    }
    public void clear()
    {
        br.setText("");
        ifsc.setText("");
        nam.setText("");
        email.setText("");
        pass.setText("");

    }
}
